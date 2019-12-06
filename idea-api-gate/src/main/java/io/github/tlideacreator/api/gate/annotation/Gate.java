package io.github.tlideacreator.api.gate.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

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
