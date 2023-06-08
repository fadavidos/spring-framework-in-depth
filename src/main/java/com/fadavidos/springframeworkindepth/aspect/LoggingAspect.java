package com.fadavidos.springframeworkindepth.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(MyLoggable)")
    public void executeLogging(){}


    /**
     * Before advice is executed before the targeted join point,
     * typically a method execution. It allows you to perform
     * certain actions or logic before the actual method execution takes place.
     * @param joinPoint
     */
    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        LOGGER.info(message.toString());
    }

    /**
     * AfterReturning advice is executed after the targeted join point,
     * specifically after a method has successfully completed its
     * execution and returned a result. AfterReturning advice does
     * not have the ability to influence the control flow or
     * modify the return value of the method.
     * @param joinPoint
     */
    @AfterReturning(value = "executeLogging()", returning = "returnValue")
    public void logMethodCallAfter(JoinPoint joinPoint, Object returnValue){
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        if(returnValue instanceof Collection r) {
            message.append(", returning: ").append(r.size());
        } else {
            message.append(", returning: ").append(returnValue.toString());
        }

        LOGGER.info(message.toString());
    }

    /**
     * advice is a type of advice that allows you to fully control the
     * execution of a join point, including the ability to modify method
     * parameters, control the method invocation, and handle the return value.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        message.append(" totalTime: ").append(totalTime).append("ms");
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        LOGGER.info(message.toString());
        return returnValue;
    }

}
