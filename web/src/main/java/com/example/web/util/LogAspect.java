package com.example.web.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static com.example.web.util.CommonUtil.*;
import static com.example.web.util.LogInterceptor.REQUEST_TIME;

@Slf4j
@Aspect
@Component
public class LogAspect {

  private static final String RESPONSE = "response";
  private static final String RESPONSE_TIME = "responseTime";
  private static final String ELAPSED_SEC = "elapsedSec";

  @Pointcut("execution(* com.example.web.controller.*.*(..))")
  private void getControllerPointCut() {

  }

  @Around(value = "getControllerPointCut()")
  public void log(ProceedingJoinPoint joinPoint) throws Throwable {
    Object response = joinPoint.proceed();

    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put(LOG_ID, getLogId());

    responseMap.put(RESPONSE, response);

    LocalDateTime requestTime = getRequestTime();
    responseMap.put(REQUEST_TIME, requestTime.toString());

    LocalDateTime responseTime = LocalDateTime.now();
    responseMap.put(RESPONSE_TIME, responseTime.toString());

    Long elapsedSec = getElapsedSec(requestTime, responseTime);
    responseMap.put(ELAPSED_SEC, elapsedSec);

    String responseLog = convertObjectToString(responseMap);

    log.info("Aspect - Response Log {}", responseLog);
  }

  private LocalDateTime getRequestTime() {
    RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
    return (LocalDateTime) ((ServletRequestAttributes) requestAttribute)
        .getRequest()
        .getAttribute(REQUEST_TIME);
  }

  private Long getElapsedSec(LocalDateTime requestTime, LocalDateTime responseTime) {
    return ChronoUnit.SECONDS.between(requestTime, responseTime);
  }

  private String getLogId() {
    RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
    return (String) ((ServletRequestAttributes) requestAttribute)
        .getRequest()
        .getAttribute(LOG_ID);
  }
}
