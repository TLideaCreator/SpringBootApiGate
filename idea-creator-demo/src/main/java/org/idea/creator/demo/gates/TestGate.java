package org.idea.creator.demo.gates;

import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;
import org.idea.creator.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Gate(value = "test")
public class TestGate implements IGateInterface {

    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }
}
