package icu.saymeevetime.backend.vo;

import icu.saymeevetime.backend.enums.ResponseErrorCodeEnum;
import icu.saymeevetime.backend.exception.BaseException;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author jiacheng
 * @date 11/3/23 11:12 pm 星期六
 * @description : 表现层公共返回
 */
@Data
@Builder
@ToString
public class BaseResponseVO<M> {

    private Integer code;

    private String message;

    private M data;


    public static BaseResponseVO success() {
        return BaseResponseVO.builder().code(ResponseErrorCodeEnum.SUCCESS.getCode()).message(ResponseErrorCodeEnum.SUCCESS.getMessage()).build();
    }

    public static <M> BaseResponseVO success(M data) {
        return BaseResponseVO.builder().code(ResponseErrorCodeEnum.SUCCESS.getCode()).message(ResponseErrorCodeEnum.SUCCESS.getMessage()).data(data).build();
    }

    public static BaseResponseVO serviceException(BaseException exception) {
        return BaseResponseVO.builder().code(exception.getCode()).message(exception.getDefaultMessage()).build();
    }

    public static BaseResponseVO serviceErrorCodeException(ResponseErrorCodeEnum errorCodeEnum, Exception e) {
        return BaseResponseVO.builder().code(errorCodeEnum.getCode()).message(e.getMessage()).build();

    }


}
