package com.Aaron.MFM.web.admin.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitAccess {
    int maxAccessCount() default 5; // 每分钟默认最大访问次数
}
