package com.general.component.log.core.annotation;


import com.general.component.log.core.callback.LogCallback;

import java.lang.annotation.*;

/**
 * @description: 日志注解
 * @author: liuyan
 * @create: 2022−10-22 10:08 AM
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OpLog {

    /**
     * 业务类型
     *
     * @return
     * @see LogConstants.SceneConstants
     */
    String scene() default "";

    /**
     * 业务名称
     *
     * @return
     */
    String value() default "";

    /**
     * 回调
     *
     * @return
     */
    Class<? extends LogCallback> callback() default LogCallback.class;
}
