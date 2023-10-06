package com.example.web.config;

import com.example.web.util.LogInterceptor;
import com.example.web.util.interceptor.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  private final LoggingInterceptor loggingInterceptor;

  @Override public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(loggingInterceptor)
        .order(1)
        .addPathPatterns("/*");
  }



//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//
//    // 만든 인터셉터 등록하기
//    registry.addInterceptor(new LogInterceptor())
//        .order(1)                  // 인터셉터 호출 순위. 낮을수록 먼저 호출
//        .addPathPatterns("/**");   // 인터셉터 적용할 URL 패턴
//    //.excludePathPatterns("");// 인터셉터 제외할 패턴 지정
//  }
}
