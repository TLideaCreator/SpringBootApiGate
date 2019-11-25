package org.idea.creator.api.result.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lqh
 */

@Data
public class Result<T> implements Serializable {
    public static final Integer SUCCESS = 0;
    private Integer code = SUCCESS;
    private String msg = null;
    private T data = null;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
