package io.github.tlideacreator.api.result.handler;

import io.github.tlideacreator.api.result.exception.ResultException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author lqh
 */
@Component
public class ResultAttributes  extends DefaultErrorAttributes {
    private Exception ex = null;
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        if(ex instanceof ResultException){
            Map<String, Object> map = new HashMap<>();
            map.put("code ", ((ResultException) ex).getCode());
            map.put("message ", ((ResultException) ex).getMessage());
            map.put("data", null);
            return map;
        }else {
            return  super.getErrorAttributes(webRequest, includeStackTrace);
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        this.ex = ex;
        return super.resolveException(request, response, handler, ex);
    }
}
