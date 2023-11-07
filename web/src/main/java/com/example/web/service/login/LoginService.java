package com.example.web.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  @Value("${oauth.naver.secret}")
  private String state;

  @Value("${oauth.naver.client-id}")
  private String clientId;

  @Value("${oauth.naver.url.auth}")
  private String oauthUrl;

  @Value("${oauth.naver.url.redirect}")
  private String redirectUrl;

  public String getRedirectUrl() {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("redirect:" + oauthUrl + "/oauth2.0/authorize?");
    stringBuilder.append("response_type=code&");
    stringBuilder.append("client_id=" + clientId + "&");
    stringBuilder.append("state=" + state + "&");
    stringBuilder.append("redirect_uri=" + redirectUrl);

    return stringBuilder.toString();
  }
}
