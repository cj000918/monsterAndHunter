package com.chenjian.others.customizeAnnotation;

import java.lang.annotation.*;

/**
 * 用来去掉日志打印的参数日志,防止有些文件参数过长
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NoArgLog {

    /**
     * 忽略请求参数
     */
    boolean omitRequest() default true;

    /**
     * 忽略响应参数
     */
    boolean omitResponse() default true;
}
