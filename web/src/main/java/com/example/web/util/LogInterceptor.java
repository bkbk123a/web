package com.example.web.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.web.util.CommonUtil.*;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

  private static final String HEADERS = "headers";
  private static final String METHOD = "method";

  private static final String REQUEST_URI = "requestUri";
  private static final String REQUEST_BODY = "requestBody" ;
  public static final String REQUEST_TIME = "requestTime";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String uuid = UUID.randomUUID().toString();
    request.setAttribute(LOG_ID, uuid);

    LocalDateTime now = LocalDateTime.now();
    request.setAttribute(REQUEST_TIME, now);

    Map<String, Object> requestMap = getRequestMap(request, handler, now);

    String requestLog = convertObjectToString(requestMap);

    log.info("PreHandle - Request Log {}", requestLog);

    // false 시 진행 하지 않는다.
    return true;
  }

  /**
   * 로깅할 주제를 key, 내용을 value 로 Request 를 Map 으로 반환
   *
   * @param request 요청
   * @param handler 핸들러
   * @return Map 으로 변환된 Request
   * @throws IOException
   */
  private Map<String, Object> getRequestMap(HttpServletRequest request, Object handler, LocalDateTime now)
      throws IOException {

    Map<String, Object> requestMap = new LinkedHashMap<>();
    requestMap.put(METHOD, request.getMethod());

    requestMap.put(LOG_ID, request.getAttribute(LOG_ID));

    String requestUri = request.getRequestURI();
    requestMap.put(REQUEST_URI, requestUri);

    Map<String, String> headers = getHeaders(request);
    requestMap.put(HEADERS, headers);

    requestMap.put(REQUEST_TIME, now.toString());
    // handler : 호출할 컨트롤러 메서드의 정보들이 있다.
    if (handler instanceof HandlerMethod) {
      String requestBody = getRequestBody(request);
      Map<String, Object> requestBodyMap = convertJsonStringToMap(requestBody);

      requestMap.put(REQUEST_BODY, requestBodyMap);
    }

    return requestMap;
  }

  public String getRequestBody(HttpServletRequest httpServletRequest) throws IOException {
    // HTTP 요청의 본문을 읽어들이기 위해 ServletInputStream을 얻어옵니다.
    InputStream inputStream = httpServletRequest.getInputStream();

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder requestBody = new StringBuilder();
    String line;

    // 한 줄씩 읽어들여서 requestBody 에 추가합니다.
    while ((line = reader.readLine()) != null) {
      requestBody.append(line);
    }

    return requestBody.toString();
  }

  public static Map<String, String> getHeaders(HttpServletRequest httpServletRequest) {
    Map<String, String> headersMap = new HashMap<>();
    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();

      if (!isAllowedHeaders(headerName)) {
        continue;
      }

      String headerBody = httpServletRequest.getParameter(headerName);

      if (headerBody != null) {
        headersMap.put(headerBody, headerName);
      }
    }

    return headersMap;
  }

  private static boolean isAllowedHeaders(String header) {
    return header.equalsIgnoreCase("user-agent")
        || header.equalsIgnoreCase("accept-encoding");
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("PostHandle [{}]", modelAndView);
  }

  // afterCompletion 에서 로깅 하는 이유
  // @Controller 단에서 Exception 발생시 PostHandle 호출 안하기 때문
  // afterCompletion 은 항상 호출 된다.
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
