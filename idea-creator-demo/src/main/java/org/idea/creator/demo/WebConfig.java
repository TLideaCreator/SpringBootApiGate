package org.idea.creator.demo;

import org.idea.creator.api.gate.interceptor.GateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getGateInterceptor()).addPathPatterns("/**");
    }

    @Bean
    GateInterceptor getGateInterceptor(){
        return new GateInterceptor();
    }
}
