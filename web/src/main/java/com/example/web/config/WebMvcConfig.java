package com.example.web.config;

import com.example.web.util.interceptor.SessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  private final SessionInterceptor sessionInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(sessionInterceptor)
        .order(1)                 // 인터셉터 호출 순위. 낮을수록 먼저 호출
        .addPathPatterns("/attend/**")
        .addPathPatterns("/item/**")
        .addPathPatterns("/login/**")
        .addPathPatterns("/oauth/**")
        .addPathPatterns("/user/**")
        .excludePathPatterns("/error"); // 인터셉터 제외할 패턴 지정
  }
}
