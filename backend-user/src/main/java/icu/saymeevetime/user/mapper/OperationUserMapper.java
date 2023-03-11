package icu.saymeevetime.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.saymeevetime.user.entity.OperationUser;

/**
 * <p>
 * 运营用户表 Mapper 接口
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
public interface OperationUserMapper extends BaseMapper<OperationUser> {

    void checkUserLogin(String name, String password);
}
