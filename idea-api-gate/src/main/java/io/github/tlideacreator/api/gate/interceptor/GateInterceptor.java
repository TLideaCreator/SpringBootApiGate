package io.github.tlideacreator.api.gate.interceptor;

import io.github.tlideacreator.api.gate.annotation.GateKeeper;
import io.github.tlideacreator.api.gate.factory.GateFactory;
import io.github.tlideacreator.api.gate.exception.GateException;
import io.github.tlideacreator.api.result.result.Result;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
            try{
                return GateFactory.getInstance().checkGateEnable(gateNames,request, response, handler);
            }catch (GateException ex){
                PrintWriter writer = null;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try{
                    writer = response.getWriter();
                    Result<Object> result = new Result<>();
                    result.setCode(ex.getCode());
                    result.setMsg(ex.getMessage());
                    writer.print(result);
                }catch (Exception e){

                }finally {
                    if(writer != null){
                        writer.close();
                    }
                }

                return false;
            }
        }
        return true;
    }


}
