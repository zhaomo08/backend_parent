package icu.saymeevetime.user.controller;


import icu.saymeevetime.backend.utils.JwtTokenUtil;
import icu.saymeevetime.backend.vo.BaseResponseVO;
import icu.saymeevetime.user.entity.dto.UserLoginDTO;
import icu.saymeevetime.user.service.IOperationUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * <p>
 * 运营用户表 前端控制器
 * </p>
 *
 * @author Chester
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/operation-user")
@AllArgsConstructor
public class OperationUserController {
    private IOperationUserService operationUserService;


    @PostMapping("/login")
    public BaseResponseVO login(@RequestBody @Valid UserLoginDTO loginDTO) {

        Long uid = operationUserService.checkUserLogin(loginDTO.getName(), loginDTO.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(String.valueOf(uid), randomKey);

        HashMap<String, String> result = new HashMap<>();
        result.put("randomkey", randomKey);
        result.put("token", token);
        return BaseResponseVO.success(result);
    }

}
