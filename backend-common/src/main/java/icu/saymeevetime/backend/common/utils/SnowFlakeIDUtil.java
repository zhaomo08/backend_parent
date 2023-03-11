package icu.saymeevetime.backend.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import java.net.UnknownHostException;

/**
 * Created by xr on 18/4/26.
 */
public class SnowFlakeIDUtil {

    private static Logger logger = LoggerFactory.getLogger(SnowFlakeIDUtil.class);

    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long twepoch = 1420041600000L;
    /**
     * 预留字段在id中占的位数
     */
    private static final long obligateBits = 5L;
    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 5L;
    /**
     * 序列在id中占的位数
     */
    private static final long sequenceBits = 12L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 支持的最大预留标识id，结果是31
     */
    private static final long maxReservedId = -1L ^ (-1L << obligateBits);
    /**
     * 机器码向左移12位
     */
    private static final long workerIdShift = sequenceBits;
    /**
     * 预留标识在ID向左移17位
     */
    private static final long obligateIdShift = workerIdShift + obligateBits;
    /**
     * 时间截向左移22位
     */
    private static final long timestampLeftShift = obligateIdShift + workerIdBits;
    /**
     * 生成序列的掩码，这里为1023
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private static long workerId = 0;

    /**
     * 毫秒内序列(0~1023)
     */
    private static long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;

    /**
     * 预留字段的ID
     */
    private static long obligateID = 0L;

    /**
     * 设置机器码
     */
    public static void setMachineCode(long code) {
        workerId = code;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳
        if (timestamp < lastTimestamp) {
            long delay = lastTimestamp - timestamp;
            //如果偏差比较小，则等待
            if (delay < 1000) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            timestamp = timeGen();

            //如果还没好，报警
            if (timestamp < lastTimestamp) {
                timeCallBackProcess(timestamp, lastTimestamp);
            } else {
                //重新分配ID
                long id = nextId();

                return id;
            }
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
                sequence = lastTimestamp & 9;
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //时间戳
                | (obligateID << obligateIdShift) //预留字段
                | (workerId << workerIdShift) //工作ID
                | sequence;
    }

    /**
     * 时钟回拨异常处理
     */
    protected static void timeCallBackProcess(long timestamp, long lastTimestamp) {
        //记录日志
        logger.info("时钟回拨发生异常，等待时间超过阀值。现在时间是：" + timestamp + ",回拨前时间是：" + lastTimestamp);
        //调用报警系统
        //从节点摘除
        //DiscoveryManager.getInstance().shutdownComponent();
        SpringApplication.exit(ApplicationContextUtil.getApplicationContext());
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] param) throws UnknownHostException {
        System.out.println("SnowFlakeIDUtil.nextId() = " + SnowFlakeIDUtil.nextId());
//        long start = System.currentTimeMillis();
//        List<Long> listAA = new ArrayList<>();
//        for(int i=0;i<1000;i++){
//            listAA.add(SnowFlakeIDUtil.nextId());
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        for(Long a : listAA){
//            System.out.println(Long.toBinaryString(a));
//        }
    }
}
