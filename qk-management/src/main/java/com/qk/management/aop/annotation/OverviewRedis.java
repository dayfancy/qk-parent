package com.qk.management.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OverviewRedis {

    String Key() default "";


    String keyPrefix() default "";


    long expireTime() default 0 ;


    TimeUnit expireTimeUnit() default TimeUnit.SECONDS;

}
