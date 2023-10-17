package com.example.web.util.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;

public class HandlerInterceptorBase implements HandlerInterceptor {

  protected boolean hasAnnotation(Object handler, Class<? extends Annotation> annotation) {
    if (!(handler instanceof HandlerMethod)) {
      return false;
    }

    return ((HandlerMethod) handler).getMethodAnnotation(annotation) != null;
  }
}
