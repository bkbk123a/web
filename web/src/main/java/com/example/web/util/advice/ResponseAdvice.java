package com.example.web.util.advice;

import com.example.web.model.oauth.JwtUser;
import com.example.web.util.container.SessionContainer;
import com.example.web.util.token.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 응답시 유저 정보 토대로 JWT 발급한다.
@ControllerAdvice
@RequiredArgsConstructor
public class ResponseAdvice<T> implements ResponseBodyAdvice<T> {

  private final JwtTokenUtil jwtTokenUtil;

  @Override
  public boolean supports(MethodParameter returnType,
                          Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType,
                           Class<? extends HttpMessageConverter<?>> selectedConverterType,
                           ServerHttpRequest request, ServerHttpResponse response) {

    JwtUser userInfo = SessionContainer.getSession();

//    ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse) response;
//    if (servletServerHttpResponse.getServletResponse().getStatus() != HttpStatus.OK.value()) {
//      return body;
//    }

    if (userInfo == null) {
      return body;
    }

    if (!jwtTokenUtil.isRefresh(userInfo)) {
      return body;
    }
    // 정상 루틴 : 유저 정보 토대로 JWT 발급
    response.getHeaders().add(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(userInfo));

    return body;
  }
}
