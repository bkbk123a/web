package com.example.web.util.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class CustomServletWrappingFilter extends OncePerRequestFilter {
  // 서블릿을 래핑하는 필터
  // OncePerRequestFilter 를 상속받아 한 request당 한번의 실행만 되도록 보장하였다.
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(wrappingRequest, wrappingResponse);

    wrappingResponse.copyBodyToResponse();
  }
}
