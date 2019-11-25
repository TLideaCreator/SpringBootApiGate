package org.idea.creator.api.gate;

import org.idea.creator.api.gate.annotation.GateScan;
import org.idea.creator.api.gate.config.GateUnit;
import org.idea.creator.api.gate.exception.GateException;
import org.idea.creator.api.gate.interceptor.GateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author lqh
 */
@Component
@Order(10)
public class GateRunner implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ApplicationReadyEvent){
            Object source = ((ApplicationReadyEvent)applicationEvent).getSource();
            SpringApplication app = (SpringApplication) source;
            GateScan scans = app.getMainApplicationClass().getAnnotation(GateScan.class);
            try {
                if(scans != null){
                    GateUnit.loadGateClass(Arrays.asList(scans.path()));
                }
            } catch (GateException ignored) { }
        }else if(applicationEvent instanceof ContextRefreshedEvent) {
            ApplicationContext appCtx = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            GateUnit.setAppContext(appCtx);
        }
    }
}
