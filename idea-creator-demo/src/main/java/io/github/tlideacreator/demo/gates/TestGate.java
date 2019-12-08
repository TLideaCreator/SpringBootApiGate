package io.github.tlideacreator.demo.gates;

import io.github.tlideacreator.api.gate.IGateInterface;
import io.github.tlideacreator.api.gate.exception.GateException;
import io.github.tlideacreator.demo.service.TestService;
import io.github.tlideacreator.api.gate.annotation.Gate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lqh
 */
@Gate(value = "test")
public class TestGate implements IGateInterface {
    @Autowired
    TestService testService;

    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws GateException {
        throw new GateException("gate test");
    }
}
