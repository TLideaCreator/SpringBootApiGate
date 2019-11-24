package org.idea.creator.api.gate.annotation;


import java.lang.annotation.*;

/**
 * @author lqh
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface GateScan {
    String[] path() default {};
}
