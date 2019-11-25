package org.idea.creator.api.gate;

import org.idea.creator.api.gate.config.GateUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lqh
 */
@Component
@Order(10)
public class GateRunner implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ContextRefreshedEvent) {
            ApplicationContext appCtx = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
            GateUnit.loadGateClass(appCtx);
        }
    }
}
