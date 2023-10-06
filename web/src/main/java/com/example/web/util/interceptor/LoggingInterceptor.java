package com.example.web.util.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

  private final ObjectMapper objectMapper;

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                              Object handler, Exception ex) throws Exception {

    final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
    final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

    log.info(
        "ReqBody : {} / ResBody : {}",
        objectMapper.readTree(cachingRequest.getContentAsByteArray()),
        objectMapper.readTree(cachingResponse.getContentAsByteArray())
    );

  }
}
