package icu.saymeevetime.backend.exception;

import icu.saymeevetime.backend.enums.ResponseErrorCodeEnum;
import icu.saymeevetime.backend.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiacheng
 * @date 11/3/23 11:27 pm 星期六
 * @description :
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponseVO handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex) {
        return BaseResponseVO.serviceErrorCodeException(ResponseErrorCodeEnum.PARAMETER_ERR, ex);
    }

    @ExceptionHandler(value = BindException.class)
    public BaseResponseVO validationExceptionHandler(BindException e) {
        return BaseResponseVO.serviceErrorCodeException(ResponseErrorCodeEnum.PARAMETER_ERR, e);
    }


    @ExceptionHandler(BaseException.class)
    public BaseResponseVO exceptionHandler(HttpServletRequest request, BaseException exception) {

        log.error("BaseServiceException,code: {},message", exception.getCode(), exception.getMessage());

        return BaseResponseVO.serviceException(exception);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return BaseResponseVO.serviceErrorCodeException(ResponseErrorCodeEnum.PARAMETER_ERR, e);
    }


}
