package com.example.web.util.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class HandlerInterceptorBase implements HandlerInterceptor {

  private static final List<String> excludeUris = Arrays.asList("login", "swagger", "api-docs");

  /**
   * 특정 애노테이션 여부 판단
   *
   * @param handler
   * @param annotation
   * @return true : 포함, false : 포함 안함
   */
  protected boolean hasAnnotation(Object handler, Class<? extends Annotation> annotation) {
    if (!(handler instanceof HandlerMethod)) {
      return false;
    }

    return ((HandlerMethod) handler).getMethodAnnotation(annotation) != null;
  }

  /**
   * 특정 URI 포함 여부 판단
   * @param request
   * @return true : 포함, false : 포함 안함
   */
  protected boolean hasUri(HttpServletRequest request) {
    String uri = request.getRequestURI();

    for(String excludeUri : excludeUris) {
      if(uri.contains(excludeUri)){
        return true;
      }
    }
    return false;
  }
}
