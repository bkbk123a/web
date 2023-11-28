package com.example.web.util.interceptor;

import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.model.exception.CustomErrorException;
import com.example.web.model.oauth.JwtUser;
import com.example.web.util.container.SessionContainer;
import com.example.web.util.token.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
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
    if (isPassInterceptor(request, handler)) {
      return true;
    }

    String accessToken = getAccessTokenOrElseThrow(request);

    JwtUser userInfo;
    try {
      // 클라이언트 요청에 따라 JWT 에서 유저 정보를 가져온 후, ThreadLocal 에 해당 유저 정보 set
      userInfo = jwtTokenUtil.getUserInfo(accessToken);
      userInfo.setExpireTime(jwtTokenUtil.getExpireTime(accessToken));
      SessionContainer.setSession(userInfo);
    } catch (ExpiredJwtException e) {
      System.out.println("Token has expired. : " + e);
    } catch (Exception e) {
      System.out.println("Token is invalid for another reason. : " + e);
    }
    return true;
  }

  /**
   * 인터셉터 통과 여부 판단
   *
   * @param request
   * @param handler
   * @return 인터셉터 통과 여부 (true : 통과, false : 통과 안함)
   */
  private boolean isPassInterceptor(HttpServletRequest request, Object handler) {
    if (handler instanceof HandlerMethod handlerMethod
        && handlerMethod.getBeanType().isAnnotationPresent(IgnoreAuth.class)) {
      return true;
    }

    return hasAnnotation(handler, IgnoreAuth.class)
        || hasUri(request);
  }

  private String getAccessTokenOrElseThrow(HttpServletRequest request) {
    String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (accessToken == null) {
      log.error("SessionInterceptor header has no AUTHORIZATION");
      throw CustomErrorException.builder().resultValue(4).build();
    }

    final String authenticationType = "Bearer";
    if (accessToken.startsWith(authenticationType)) {
      accessToken = accessToken.substring(authenticationType.length());
    }

    return accessToken;
  }
}
