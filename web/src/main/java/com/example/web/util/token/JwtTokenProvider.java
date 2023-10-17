package com.example.web.util.token;

import com.example.web.model.oauth.JwtUser;
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

  private static final String USER_INFO = "userInfo";
  private final Key key;

  public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);  //키 생성
  }

  /**
   * jwt 토큰 생성
   *
   * @param expiredAt 만료 시간
   * @param jwtUser   claim 에 들어갈 유저 정보
   * @return          jwt 토큰
   */
  public String generateToken(Date expiredAt, JwtUser jwtUser) {
    return Jwts.builder()
        .setHeaderParam("type", "JWT")
        .claim(USER_INFO, jwtUser)
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
