package com.example.web.util.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private static final String USER_INDEX = "userIndex";
  private final Key key;

  public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);  //키 생성
  }

  /**
   * jwt 토큰 생성
   *
   * @param subject   페이로드 정보(claim 정보)
   * @param expiredAt 만료 시간
   * @return          jwt 토큰
   */
  public String generate(long userIndex, Date expiredAt) {
    return Jwts.builder()
        .setHeaderParam("type", "JWT")
        .claim(USER_INDEX, Long.toString(userIndex))
        .setExpiration(expiredAt) // 민료 시간
        .signWith(key, SignatureAlgorithm.HS512)
        .compact(); // 토큰 생성
  }

  public String extractSubject(String accessToken) {
    Claims claims = parseClaims(accessToken);
    return claims.getSubject();
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
}
