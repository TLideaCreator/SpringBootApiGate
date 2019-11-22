package org.idea.creator.api.gate.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.*;

/**
 * @author lqh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface Gate {
    @AliasFor(annotation = Component.class)
    String value() default "";

}
