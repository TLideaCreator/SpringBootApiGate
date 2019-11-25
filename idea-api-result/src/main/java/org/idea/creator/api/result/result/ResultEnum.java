package org.idea.creator.api.result.result;

/**
 * @author lqh
 */

public enum ResultEnum {
    //空数据时使用
    NULL(),
    //数据类型为string时使用
    STRING();

    private Object data;

    ResultEnum() {
    }

    public Object getData() {
        return data;
    }

    public ResultEnum setData(Object data) {
        this.data = data;
        return this;
    }

}
