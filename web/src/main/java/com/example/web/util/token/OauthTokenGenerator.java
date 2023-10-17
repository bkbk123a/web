package com.example.web.util.token;

import com.example.web.model.oauth.token.OauthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class OauthTokenGenerator {

  @Value("${jwt.access_token_expire_milsec}")
  private long accessTokenExpireMilSec;

  @Value("${jwt.refresh_token_expire_milsec}")
  private long refreshTokenExpireMilSec;

  @Value("${jwt.token_type}")
  private String tokenType;

  private final JwtTokenProvider jwtTokenProvider;

  public OauthToken generate(Long userIndex) {
    long nowSec = (new Date()).getTime();
    Date accessTokenExpiredAt = new Date(nowSec + accessTokenExpireMilSec);
    Date refreshTokenExpiredAt = new Date(nowSec + refreshTokenExpireMilSec);

    String accessToken = jwtTokenProvider.generate(userIndex, accessTokenExpiredAt);
    String refreshToken = jwtTokenProvider.generate(userIndex, refreshTokenExpiredAt);

    return OauthToken.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .tokenType(tokenType)
        .expiresIn(refreshTokenExpireMilSec / 1000L)
        .build();
  }
}
