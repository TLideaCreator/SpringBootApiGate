package org.idea.creator.api.result.handler;

import org.idea.creator.api.result.result.Result;
import org.idea.creator.api.result.annotation.ResultResponse;
import org.idea.creator.api.result.exception.ResultException;
import org.idea.creator.api.result.interceptor.RequestInterceptor;
import org.idea.creator.api.result.result.ResultEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lqh
 */
@ControllerAdvice
@Component
public class ResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest  request = sra != null ? sra.getRequest(): null;
        ResultResponse result = request!= null? (ResultResponse) request.getAttribute(RequestInterceptor.RESPONSE_RESULT_ANN) : null;
        return result != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(mediaType.getType().equals("application") && mediaType.getSubtype().equals("json")){
            Result result = new Result();
            if(body instanceof ResultEnum){
                result.setCode(Result.SUCCESS);
                result.setMsg("success");
                result.setData(((ResultEnum) body).getData());
            }else {
                result.setCode(Result.SUCCESS);
                result.setMsg("success");
                result.setData(body);
            }
            return result;
        }else{
            return body;
        }

    }
}
