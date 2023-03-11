package icu.saymeevetime.backend.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiacheng
 * @date 11/3/23 4:57 pm 星期六
 * @description :
 */

public enum ResponseErrorCodeEnum {
    SUCCESS(0, "SUCCESS", "成功"),
    SYSTEM_ERR(1, "SYSTEM_ERR", "系统异常"),
    UNKNOWN_ERR(-1, "UNKNOWN_ERR", "未知错误");


    private Integer code;
    private String name;
    private String message;

    private ResponseErrorCodeEnum(Integer code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public static boolean contains(Integer code) throws NullPointerException {
        if (null == code) {
            throw new NullPointerException("constant code is null");
        } else {
            ResponseErrorCodeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                ResponseErrorCodeEnum eum = var1[var3];
                if (code.equals(eum.getCode())) {
                    return true;
                }
            }

            return false;
        }
    }

    public static ResponseErrorCodeEnum valueOf(Integer code) throws NullPointerException, EnumConstantNotPresentException {
        if (null == code) {
            throw new NullPointerException("constant code is null");
        } else {
            ResponseErrorCodeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                ResponseErrorCodeEnum responseErrorCodeEnum = var1[var3];
                if (code.equals(responseErrorCodeEnum.getCode())) {
                    return responseErrorCodeEnum;
                }
            }

            throw new EnumConstantNotPresentException(ResponseErrorCodeEnum.class, code.toString());
        }
    }

    public static Map<String, Integer> codes() {
        ResponseErrorCodeEnum[] values = values();
        Map<String, Integer> codes = new HashMap(values.length);
        ResponseErrorCodeEnum[] var2 = values;
        int var3 = values.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            ResponseErrorCodeEnum value = var2[var4];
            codes.put(value.name(), value.getCode());
        }

        return codes;
    }

    public static Map<String, String> descs() {
        ResponseErrorCodeEnum[] values = values();
        Map<String, String> descs = new HashMap(values.length);
        ResponseErrorCodeEnum[] var2 = values;
        int var3 = values.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            ResponseErrorCodeEnum value = var2[var4];
            descs.put(value.name, value.message);
        }

        return descs;
    }

    public static Map<Integer, String> codeMsgs() {
        ResponseErrorCodeEnum[] values = values();
        Map<Integer, String> descs = new HashMap(values.length);
        ResponseErrorCodeEnum[] var2 = values;
        int var3 = values.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            ResponseErrorCodeEnum value = var2[var4];
            descs.put(value.code, value.message);
        }

        return descs;
    }

    public static String getMsgByCode(Integer code) {
        return valueOf(code).message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "ResponseErrorCodeEnum{code=" + this.code + ", name='" + this.name + '\'' + ", message='" + this.message + '\'' + '}';
    }
}

