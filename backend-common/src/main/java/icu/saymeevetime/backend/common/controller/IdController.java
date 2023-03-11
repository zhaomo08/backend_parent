package icu.saymeevetime.backend.common.controller;

import icu.saymeevetime.backend.common.utils.ServiceResponse;
import icu.saymeevetime.backend.common.utils.SnowFlakeIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiacheng
 * @date 11/3/23 4:55 pm 星期六
 * @description :
 */
@RestController
public class IdController {

    private static Logger logger = LoggerFactory.getLogger(IdController.class);

    /**
     * 一次性请求ID的上限
     **/
    private final static int limitNum = 10000;

    /**
     * 获取ID
     */

    public long nextId() {
        long id = SnowFlakeIDUtil.nextId();

        return id;
    }

    public ServiceResponse<String> nextIdStr() {
        ServiceResponse<String> resp = new ServiceResponse<String>();
        long id = SnowFlakeIDUtil.nextId();
        resp.setData("" + id);
        return resp;
    }


    public List<Long> mulNextId(@RequestParam(value = "num", defaultValue = "1") Integer num) {
        if (num == null) {
            return null;
        }
        //批量获取的上限是100
        int idNum = num;
        if (idNum > limitNum) {
            idNum = limitNum;
        }
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < idNum; i++) {
            idList.add(SnowFlakeIDUtil.nextId());
        }
        return idList;
    }

}
