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
@Gate(value="Auth")
public class AuthGate implements IGateInterface {

    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }
}
