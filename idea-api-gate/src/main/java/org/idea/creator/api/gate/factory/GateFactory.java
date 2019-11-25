package org.idea.creator.api.gate.factory;

import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;
import org.idea.creator.api.gate.exception.GateException;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author lqh
 */

public class GateFactory {


    private Map<String, IGateInterface> classHashMap = new HashMap<>();

    private static GateFactory mInstance = new GateFactory();

    private GateFactory() {
    }

    public static GateFactory getInstance() {
        return mInstance;
    }


    public void loadGateClass(ApplicationContext context){
        for (String beanName: context.getBeanDefinitionNames()) {
            Object object = context.getBean(beanName);
            if(object instanceof IGateInterface){
                Gate gate = object.getClass().getAnnotation(Gate.class);
                String value = gate.value();
                classHashMap.put(value, (IGateInterface) object);
            }
        }
    }

    public boolean checkGateEnable(Set<String> gateName, HttpServletRequest request, HttpServletResponse response, Object handler) throws GateException{
        for (String gate : gateName) {
            IGateInterface gateInterface = classHashMap.get(gate);
            if(gateInterface == null){
                throw new GateException(404, "gate with name "+gate+" not exist");
            }
            if(!gateInterface.handler(request, response, handler)){
                throw new GateException("gate "+ gate +" valid failed!");
            }
        }
        return true;
    }
}
