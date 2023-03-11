package icu.saymeevetime.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.saymeevetime.backend.enums.ResponseErrorCodeEnum;
import icu.saymeevetime.backend.exception.BaseException;
import icu.saymeevetime.backend.utils.Md5Utils;
import icu.saymeevetime.user.entity.OperationUser;
import icu.saymeevetime.user.mapper.OperationUserMapper;
import icu.saymeevetime.user.service.IOperationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 运营用户表 服务实现类
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@Service
@AllArgsConstructor
public class OperationUserServiceImpl extends ServiceImpl<OperationUserMapper, OperationUser> implements IOperationUserService {

    private OperationUserMapper userMapper;

    @Override
    public Long checkUserLogin(String name, String password) {
        LambdaQueryWrapper<OperationUser> operationUserLambdaQuery = new LambdaQueryWrapper<>();
        operationUserLambdaQuery.eq(OperationUser::getName, name);
        operationUserLambdaQuery.eq(OperationUser::getPassword, password);

        OperationUser operationUser = userMapper.selectOne(operationUserLambdaQuery);
        if (Objects.nonNull(operationUser)) {

        } else {
            throw new BaseException(null, ResponseErrorCodeEnum.USER_NOT_EXIST.getCode(), null, ResponseErrorCodeEnum.USER_NOT_EXIST.getMessage());
        }
        if (Md5Utils.encrypt(operationUser.getPassword()).equals(password)) {
            throw new BaseException(null, ResponseErrorCodeEnum.LOGIN_ERR.getCode(), null, ResponseErrorCodeEnum.LOGIN_ERR.getMessage());
        } else {
            return operationUser.getId();
        }
    }
}
