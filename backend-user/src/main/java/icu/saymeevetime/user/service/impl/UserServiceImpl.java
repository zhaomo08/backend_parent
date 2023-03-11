package icu.saymeevetime.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.saymeevetime.user.entity.User;
import icu.saymeevetime.user.mapper.UserMapper;
import icu.saymeevetime.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
