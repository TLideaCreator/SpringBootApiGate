package io.github.tlideacreator.api.result.annotation;

import java.lang.annotation.*;

/**
 * @author lqh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResultResponse {
}
