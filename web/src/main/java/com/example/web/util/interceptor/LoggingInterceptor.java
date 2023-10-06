package com.example.web.util.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.web.util.CommonUtil.*;
import static com.example.web.util.CommonUtil.convertObjectToString;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

  private static final String METHOD = "method";

  private static final String REQUEST_URI = "requestUri";
  private static final String REQUEST_BODY = "requestBody";

  private static final String RESPONSE_BODY = "responseBody";
  private static final String RESPONSE_TIME = "responseTime";
  private static final String ELAPSED_MILLISEC = "elapsedMilliSec"; // 요청 들어와서 응답 할때까지 걸린 시간

  private final ObjectMapper objectMapper;

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                              Object handler, Exception ex) throws Exception {

    final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
    final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

    LocalDateTime requestTime = (LocalDateTime) cachingRequest.getRequest().getAttribute(REQUEST_TIME);

    String requestLog = convertObjectToString(getRequestMap(requestTime, cachingRequest));
    String responseLog = convertObjectToString(getResponseMap(requestTime, cachingResponse));

    log.info(
        "Request : {} / Response : {}",
        requestLog, responseLog
    );
  }

  private Map<String, Object> getRequestMap(LocalDateTime requestTime, ContentCachingRequestWrapper cachingRequest)
      throws IOException {
    HttpServletRequest request = (HttpServletRequest) cachingRequest.getRequest();

    Map<String, Object> requestMap = new LinkedHashMap<>();

    requestMap.put(METHOD, request.getMethod());
    requestMap.put(REQUEST_URI, request.getRequestURI());
    requestMap.put(REQUEST_TIME, requestTime.toString());

    String requestBody = objectMapper.readTree(cachingRequest.getContentAsByteArray()).toString();
    Map<String, Object> requestBodyMap = convertJsonStringToMap(requestBody);

    requestMap.put(REQUEST_BODY, requestBodyMap);

    return requestMap;
  }

  private Map<String, Object> getResponseMap(LocalDateTime requestTime, ContentCachingResponseWrapper cachingResponse)
      throws IOException {
    Map<String, Object> responseMap = new HashMap<>();

    LocalDateTime responseTime = LocalDateTime.now();
    responseMap.put(RESPONSE_TIME, responseTime.toString());

    Long elapsedMilliSec = getLocalDateTimeDifferenceMilliSec(requestTime, responseTime);
    responseMap.put(ELAPSED_MILLISEC, elapsedMilliSec);

    String responseBody = objectMapper.readTree(cachingResponse.getContentAsByteArray()).toString();
    responseMap.put(RESPONSE_BODY, responseBody);

    return responseMap;
  }
}
