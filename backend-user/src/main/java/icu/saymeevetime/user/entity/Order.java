package icu.saymeevetime.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 影院编号
     */
    private Long cinemaId;

    /**
     * 电影编号
     */
    private Long filmId;

    /**
     * 已售作为编号
     */
    private String seatIds;

    /**
     * 已售座位名称
     */
    private String seatsName;

    /**
     * 影片售价
     */
    private BigDecimal filmPrice;

    /**
     * 订单总金额
     */
    private BigDecimal orderPrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 下单人
     */
    private Long userId;

    /**
     * 下单状态 0 待支付 1 已支付  2 已关闭
     */
    private Integer orderStatus;

    /**
     * 删除标识  0 未删除 1 已删除
     */
    private Integer delFlag;


}
