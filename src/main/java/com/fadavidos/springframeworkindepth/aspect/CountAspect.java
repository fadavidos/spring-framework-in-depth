package com.fadavidos.springframeworkindepth.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CountAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountAspect.class);

    private static final Map<String, Integer> countingMap = new HashMap<>();

    @Pointcut("@annotation(MyCountable)")
    public void executeCount(){}

    @Before(value = "executeCount()")
    public void count(JoinPoint joinPoint) {
        Integer initialValue = 1;
        String methodName = joinPoint.getSignature().getName();
        if (countingMap.containsKey(methodName)) {
            Integer currentValue = countingMap.get(methodName);
            currentValue++;
            countingMap.put(methodName, currentValue);
        } else {
            countingMap.put(methodName, initialValue);
        }
        StringBuilder message = new StringBuilder("Current counts are: | ");
        countingMap.forEach((k, v) -> message.append(String.format("%s :: %s |", k, v)));
        LOGGER.info(message.toString());
    }
}
