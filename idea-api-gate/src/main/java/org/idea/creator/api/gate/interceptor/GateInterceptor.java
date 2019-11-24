package org.idea.creator.api.gate.interceptor;

import org.idea.creator.api.gate.annotation.GateKeeper;
import org.idea.creator.api.gate.config.GateUnit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author lqh
 */


public class GateInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            final HandlerMethod hm = (HandlerMethod) handler;
            final Class<?> clazz = hm.getBeanType();
            final Method method = hm.getMethod();
            Set<String> gateNames = new LinkedHashSet <>();
            if(clazz.isAnnotationPresent(GateKeeper.class)){
                final GateKeeper gateKeeper = clazz.getAnnotation(GateKeeper.class);
                gateNames.addAll(Arrays.asList(gateKeeper.gates()));
            }
            if(method.isAnnotationPresent(GateKeeper.class)){
                final GateKeeper gateKeeper = method.getAnnotation(GateKeeper.class);
                gateNames.addAll(Arrays.asList(gateKeeper.gates()));
            }
            return GateUnit.checkGateEnable(gateNames,request, response, handler);
        }
        return false;
    }
}
