package icu.saymeevetime.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.saymeevetime.user.entity.OperationUser;
import icu.saymeevetime.user.mapper.OperationUserMapper;
import icu.saymeevetime.user.service.IOperationUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运营用户表 服务实现类
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@Service
public class OperationUserServiceImpl extends ServiceImpl<OperationUserMapper, OperationUser> implements IOperationUserService {

}
