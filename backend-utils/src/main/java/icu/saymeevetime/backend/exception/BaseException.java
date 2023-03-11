package icu.saymeevetime.backend.exception;


import lombok.Data;

/**
 * 基础异常
 *
 * @author tienchin
 */
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, Integer code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }


    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }


    public BaseException(Integer code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(Integer code, String defaultMessage) {
        this(null, code, null, defaultMessage);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

}
