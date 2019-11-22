package org.idea.creator.api;

import org.idea.creator.api.gate.interceptor.GateInterceptor;
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
        registry.addInterceptor(this.getGateInterceptor()).addPathPatterns("/**");
    }

    @Bean
    GateInterceptor getGateInterceptor(){
        return new GateInterceptor();
    }
}
