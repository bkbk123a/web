package com.example.web.util.token;

import com.example.web.model.oauth.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthTokenGenerator {

  @Value("${jwt.access_token_expire_milsec}")
  private long accessTokenExpireMilSec;

  private final JwtTokenUtil jwtTokenUtil;

  public String generateToken(Long userIndex) {
    long nowSec = (new Date()).getTime();
    Date accessTokenExpiredAt = new Date(nowSec + accessTokenExpireMilSec);

    JwtUser jwtUser = JwtUser.builder()
        .userIndex(userIndex)
        .build();

    String accessToken = jwtTokenUtil.generateToken(accessTokenExpiredAt, jwtUser);

    return accessToken;
  }
}
