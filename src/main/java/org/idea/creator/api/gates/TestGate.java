package org.idea.creator.api.gates;

import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;
import org.idea.creator.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lqh
 */
@Gate(value = "test")
public class TestGate implements IGateInterface {
    @Autowired
    TestService service;

    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return service.check();
    }
}
