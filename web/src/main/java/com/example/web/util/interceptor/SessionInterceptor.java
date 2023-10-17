package com.example.web.util.interceptor;

import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.model.oauth.JwtUser;
import com.example.web.util.container.SessionContainer;
import com.example.web.util.token.JwtTokenUtil;
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

  private final JwtTokenUtil jwtTokenUtil;

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

    String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (accessToken == null) {
      log.error("SessionInterceptor header has no AUTHORIZATION");
      return false;
    }

    JwtUser userInfo;
    try {
      userInfo = jwtTokenUtil.getUserInfo(accessToken);
      SessionContainer.setSession(userInfo);
    } catch (Exception e) {
      System.out.println("e = " + e);
    }
    return true;
  }
}
