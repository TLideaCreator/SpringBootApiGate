package org.idea.creator.api.result.exception;

/**
 * 通用错误类型
 * @author lqh
 */

public class ResultException extends Exception {
    private Integer code = -1;

    public ResultException() {
        super();
    }

    public ResultException(Integer code , String msg){
        super(msg);
        this.code = code;
    }

    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    protected void setCode(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
