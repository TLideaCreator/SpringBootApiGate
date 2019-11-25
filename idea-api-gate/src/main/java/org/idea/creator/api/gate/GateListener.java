package org.idea.creator.api.gate;

import org.idea.creator.api.gate.factory.GateFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lqh
 */
@Component
@Order(10)
public class GateListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        GateFactory.getInstance().loadGateClass(event.getApplicationContext());
    }
}
