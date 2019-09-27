/**
 * projectName: mh
 * fileName: SpringContextHolder.java
 * packageName: com.chenjian.others
 * date: 2019-09-27 21:42
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.others;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-27 21:42
 **/
@Service
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext ctx = null;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        SpringContextHolder.ctx = ctx;
    }

    public static Object getBean(String name) {
        return ctx.getBean(name);
    }

    public static Object getBean(Class clazz) {
        return ctx.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class clazz) {
        return ctx.getBeansOfType(clazz);
    }
}