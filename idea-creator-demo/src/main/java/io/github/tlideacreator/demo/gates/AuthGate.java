package io.github.tlideacreator.demo.gates;

import io.github.tlideacreator.api.gate.IGateInterface;
import io.github.tlideacreator.api.gate.annotation.Gate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Gate(value = "auth")
public class AuthGate implements IGateInterface {
    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }
}
