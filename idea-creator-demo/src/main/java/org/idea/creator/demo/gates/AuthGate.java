package org.idea.creator.demo.gates;

import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Gate(value = "auth")
public class AuthGate implements IGateInterface {
    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }
}
