package icu.saymeevetime.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.saymeevetime.user.entity.Order;
import icu.saymeevetime.user.mapper.OrderMapper;
import icu.saymeevetime.user.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
