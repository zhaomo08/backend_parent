package icu.saymeevetime.user.controller;


import icu.saymeevetime.backend.common.controller.IdController;
import icu.saymeevetime.user.entity.OperationUser;
import icu.saymeevetime.user.service.IOperationUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/add")
    public void add(OperationUser operationUser) {

        IdController idController = new IdController();

        OperationUser operationUser1 = new OperationUser();
        operationUser1.setId(idController.nextId());
        operationUser1.setName("test");
        operationUser1.setPassword("2312");
        operationUser1.setPhone("17629299160");

        operationUserService.save(operationUser1);

        operationUserService.list(null).stream().forEach(System.out::println);
    }

}
