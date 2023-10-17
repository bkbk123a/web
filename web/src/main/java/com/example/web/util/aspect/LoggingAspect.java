package com.example.web.util.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.web.util.CommonUtil.*;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingAspect {

  private static final String METHOD = "method";

  private static final String REQUEST_URI = "requestUri";
  private static final String REQUEST_BODY = "requestBody";

  private static final String RESPONSE_BODY = "responseBody";
  private static final String RESPONSE_TIME = "responseTime";
  private static final String ELAPSED_MILLISEC = "elapsedMilliSec"; // 요청 들어와서 응답 할때까지 걸린 시간

  @Pointcut("execution(* com.example.web.controller.*.*(..))")
  private void getControllerPointCut() {
  }

  @Around(value = "getControllerPointCut()")
  public Object processLog(ProceedingJoinPoint joinPoint) throws Throwable {

    LocalDateTime requestTime = LocalDateTime.now();

    Object response = joinPoint.proceed();
    String requestLog = convertObjectToString(getRequestMap(requestTime));
    String responseLog = convertObjectToString(getResponseMap(requestTime, response));

    log.info(
        "Request : {} / Response : {}",
        requestLog, responseLog
    );

    return response;
  }


  private Map<String, Object> getRequestMap(LocalDateTime requestTime) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();

    Map<String, Object> requestMap = new LinkedHashMap<>();

    requestMap.put(METHOD, request.getMethod());
    requestMap.put(REQUEST_URI, request.getRequestURI());
    requestMap.put(REQUEST_TIME, requestTime.toString());

    Map<String, String[]> paramMap = request.getParameterMap();

    requestMap.put(REQUEST_BODY, paramMap);

    return requestMap;
  }

  private Map<String, Object> getResponseMap(LocalDateTime requestTime, Object response) {
    Map<String, Object> responseMap = new HashMap<>();

    LocalDateTime responseTime = LocalDateTime.now();
    responseMap.put(RESPONSE_TIME, responseTime.toString());

    Long elapsedMilliSec = getLocalDateTimeDifferenceMilliSec(requestTime, responseTime);
    responseMap.put(ELAPSED_MILLISEC, elapsedMilliSec);

    responseMap.put(RESPONSE_BODY, response);

    return responseMap;
  }
}
