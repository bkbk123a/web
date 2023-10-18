package com.example.web.util.token;

import com.example.web.model.oauth.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

  @Value("${jwt.access_token_expire_milsec}")
  private long accessTokenExpireMilSec;

  @Value("${jwt.refresh_token_expire_milsec}")
  private long refreshTokenExpireMilSec;

  private static final String USER_INFO = "userInfo";

  private final Key key;

   public JwtTokenUtil(@Value("${jwt.secret-key}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);  //키 생성
  }

  /**
   * jwt 토큰 생성
   *
   * @param jwtUser claim 에 들어갈 유저 정보
   * @return jwt 토큰
   */
  public String generateToken(JwtUser jwtUser) {
    return Jwts.builder()
        .setHeaderParam("type", "JWT")
        .claim(USER_INFO, jwtUser)
        .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireMilSec)) // 민료 시간
        .signWith(key, SignatureAlgorithm.HS512)
        .compact(); // 토큰 생성
  }

  public JwtUser getUserInfo(@NonNull String accessToken) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jwtUser = objectMapper.writeValueAsString(
        parseClaims(accessToken).get(USER_INFO));

    return objectMapper.readValue(jwtUser, JwtUser.class);  // JSON string to clazz
  }

  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(accessToken)
          .getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  public long getExpireTime(@NonNull String accessToken) {
    return parseClaims(accessToken).getExpiration().getTime();
  }

  public boolean isRefresh(@NonNull JwtUser userInfo) {
    return userInfo.getExpireTime() < System.currentTimeMillis() + refreshTokenExpireMilSec;
  }
}
