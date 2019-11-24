package org.idea.creator.api.gate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lqh
 */
public interface IGateInterface {
    /**
     * 处理事件
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler Object
     * @return boolean
     */
    boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler);
}
