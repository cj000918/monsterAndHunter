package com.chenjian.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class LogAspect extends AbstractLogAspect{

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution ( * com.chenjian.controller..*.*(..))")
    @Override
    public void cutPoint() {

    }
}
