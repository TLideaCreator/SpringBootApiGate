package io.github.tlideacreator.demo;

import io.github.tlideacreator.api.gate.interceptor.GateInterceptor;
import io.github.tlideacreator.api.result.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lqh
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new GateInterceptor()).addPathPatterns("/**");
    }
}
