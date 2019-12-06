package io.github.tlideacreator.api.result.interceptor;

import io.github.tlideacreator.api.result.annotation.ResultResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 *
 * @author lqh
 */

@Component
public class RequestInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod() ;
            if(clazz.isAnnotationPresent(ResultResponse.class)){
                request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResultResponse.class));
            }else{
                request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResultResponse.class));
            }
        }
        return true;
    }
}
