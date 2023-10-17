package com.example.web.util.interceptor;

import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.model.oauth.JwtUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessionInterceptor extends HandlerInterceptorBase {

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) {
    if (handler instanceof HandlerMethod handlerMethod
        && handlerMethod.getBeanType().isAnnotationPresent(IgnoreAuth.class)) {
      return true;
    }

    if (hasAnnotation(handler, IgnoreAuth.class)) {
      return true;
    }

    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null) {
      log.error("SessionInterceptor header has no AUTHORIZATION");
      return false;
    }

    JwtUser userInfo;
    try {

    } catch (Exception e) {

    }
    return true;
  }
}
