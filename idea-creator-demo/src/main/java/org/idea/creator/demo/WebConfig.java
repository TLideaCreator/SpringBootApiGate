package org.idea.creator.demo;

import org.idea.creator.api.gate.interceptor.GateInterceptor;
import org.idea.creator.api.result.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
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
        registry.addInterceptor(new GateInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
    }
}
