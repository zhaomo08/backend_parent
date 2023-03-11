package icu.saymeevetime.backend.common.utils;

import icu.saymeevetime.backend.enums.ResponseErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiacheng
 * @date 11/3/23 4:56 pm 星期六
 * @description :
 */


@Data
public final class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = -907447282315330802L;
    private Integer code;
    private String msg;
    private T data;
    private Map<Object, Object> exts;

    public ServiceResponse() {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = 0;
    }

    public ServiceResponse(Integer code) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = code;
        this.msg = null;
    }

    public ServiceResponse(Integer code, String msg) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = code;
        this.msg = msg;
    }

    public ServiceResponse(Integer code, String msg, T data) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResponse(ResponseErrorCodeEnum errorCodeEnum, T data) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
        this.data = data;
    }

    public ServiceResponse(ResponseErrorCodeEnum errorCodeEnum) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
        this.data = null;
    }

    public ServiceResponse(Integer code, String msg, T data, Map<Object, Object> exts) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.exts = exts;
    }

    public ServiceResponse(ResponseErrorCodeEnum errorCodeEnum, T data, Map<Object, Object> exts) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
        this.data = data;
        this.exts = exts;
    }

    public ServiceResponse(ResponseErrorCodeEnum errorCodeEnum, Map<Object, Object> exts) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
        this.data = null;
        this.exts = exts;
    }

    public ServiceResponse(T data) {
        this.code = ResponseErrorCodeEnum.SUCCESS.getCode();
        this.msg = ResponseErrorCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public void addExt(Object key, Object value) {
        if (null == this.exts) {
            this.exts = new HashMap(1);
        }

        this.exts.put(key, value);
    }

    public Object getExt(Object key) {
        return null == this.exts ? null : this.exts.get(key);
    }


    public boolean isSucceed() {
        return this.code == 0;
    }

    public boolean isSucceedAndHasContent() {
        return this.code == 0 && this.data != null;
    }
}

