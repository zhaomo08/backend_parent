package icu.saymeevetime.user.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiacheng
 * @date 11/3/23 10:55 pm 星期六
 * @description :
 */
@Data
public class UserLoginDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

}
