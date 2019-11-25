package org.idea.creator.api.gate.exception;

import org.idea.creator.api.result.exception.ResultException;

/**
 * @author lqh
 */
public class GateException extends ResultException {
    public GateException() {
        super();
        this.setCode(403);
    }

    public GateException(Integer code, String msg) {
        super(code, msg);
    }

    public GateException(String message) {
        super(message);
        this.setCode(403);
    }

    public GateException(Throwable throwable) {
        super("gate error ", throwable);
        this.setCode(403);
    }


    public GateException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(403);
    }

}
