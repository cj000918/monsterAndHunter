package com.chenjian.aop;

import com.alibaba.fastjson.JSON;
import com.chenjian.entity.response.RestResponse;
import com.chenjian.others.ServiceContext;
import com.chenjian.others.customizeAnnotation.NoArgLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract void cutPoint();

    @Around("cutPoint()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        boolean infoFlag = logger.isInfoEnabled();
        String uri = null;
        try {
            // 获取request后得到uri
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                if (request != null) {
                    uri = request.getRequestURI();
                }
            }
        } catch (Exception e) {
            logger.error("该异常不影响主流程:" + e.getMessage(), e);
        }
        String reqId = ServiceContext.getContext().getRequestId();
        if(ObjectUtils.isEmpty(reqId)){
            reqId = UUID.randomUUID().toString();
            ServiceContext.getContext().setAttachment("yes.req.requestId", reqId);
        }

        //过滤掉request和response
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        NoArgLog noArgLog = AnnotationUtils.findAnnotation(methodSignature.getMethod(), NoArgLog.class);
        Class returnType = methodSignature.getReturnType();
        logger.info("returnType:{}",returnType);
        boolean ommitRequest = false;
        boolean ommitResponse = false;
        if (noArgLog != null) {
            ommitRequest = noArgLog.omitRequest();
            ommitResponse = noArgLog.omitResponse();
        }

        String argString = "";
        if (!ommitRequest) {
            Object[] args = joinPoint.getArgs();
            List<Object> logArgs = Stream.of(args)
                    .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                    .collect(Collectors.toList());
            argString = JSON.toJSONString(logArgs);
        }

        logger.info("API start===>>> reqId:{}, uri:{}, method:{}.{}(), params:{}", reqId, uri,
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                argString);
        Object obj = null;
        Long end = null;
        long start = System.currentTimeMillis();
        try {
            String responseString = null;
            if (!ommitResponse) {
                obj = joinPoint.proceed();
                if(obj instanceof String){
                    responseString = obj.toString();
                }else {
                    responseString = JSON.toJSONString(obj);
                }
            }
            end = System.currentTimeMillis() - start;
            logger.info("API start===>>> reqId:{}, uri:{}, method:{}.{}(), 《-----》API end===>>> elapsed time:{}ms, response data:{}",
                    reqId, uri, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                    end, responseString);
            return obj;
        }
//        catch (LGBizException e) {
//            logger.info(e.getMessage(), e);
//            if(returnType != RestResponse.class){
//                throw e;
//            }
//            logger.info("API start===>>> reqId:{}, uri:{}, method:{}.{}(), 《-----》API throws exception===>>> elapsed time:{}ms, exception:{}",
//                    reqId, uri, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
//                    end, e.getMessage());
//            return new RestResponse<>();
//        } catch (BusinessRuntimeException e) {
//            logger.error(e.getMessage(), e);
//            if(returnType != RestResponse.class){
//                throw e;
//            }
//            logger.error("API start===>>> reqId:{}, uri:{}, method:{}.{}(), 《-----》API throws exception===>>> elapsed time:{}ms, exception:{}",
//                    reqId, uri, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
//                    end, e.getMessage());
//            return new RestResponse<>(e.getCode(), e.getRawMessage());
//        }

        catch (Exception e) {
            end = System.currentTimeMillis() - start;
            logger.error(e.getMessage(), e);
            if(returnType != RestResponse.class){
                throw e;
            }
            logger.error("API start===>>> reqId:{}, uri:{}, method:{}.{}(), 《-----》API throws exception===>>> elapsed time:{}ms, exception:{}",
                    reqId, uri, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                    end, e.getMessage());
            return new RestResponse<>(RestResponse.DEFAULT_ERR_CODE, getLastThrowable(e).getMessage());
        }
    }

    private Throwable getLastThrowable(Exception e) {
        if (null == e) {
            return e;
        }
        Throwable next = e;
        while (null != next.getCause()) {
            next = next.getCause();
        }
        return next;
    }

}
