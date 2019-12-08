package io.github.tlideacreator.api.gate;

import io.github.tlideacreator.api.gate.exception.GateException;

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
     * @throws GateException
     * @return boolean
     */
    boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws GateException;
}
