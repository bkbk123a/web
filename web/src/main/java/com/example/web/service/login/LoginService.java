package com.example.web.service.login;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.oauth.info.NaverUserInfo;
import com.example.web.service.login.oauth.NaverOauthService;
import com.example.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
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

  private final NaverOauthService naverOauthService;
  private final UserService userService;

  public String getRedirectUrl() {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("redirect:" + oauthUrl + "/oauth2.0/authorize?");
    stringBuilder.append("response_type=code&");
    stringBuilder.append("client_id=" + clientId + "&");
    stringBuilder.append("state=" + state + "&");
    stringBuilder.append("redirect_uri=" + redirectUrl);

    return stringBuilder.toString();
  }

  public OauthNaverLoginDto.Response processNaverLogin(OauthNaverLoginDto.Request request) {

    String accessToken = naverOauthService.processAccessToken(request);

    NaverUserInfo naverUserInfo = naverOauthService.processUserInfo(accessToken);

    Optional<UserInfo> userInfo = userService.getUserInfoByEmail(naverUserInfo.getEmailAddress());

    boolean isNewUSer = userInfo.isEmpty();

    if (!isNewUSer) {
      return userService.login(isNewUSer, userInfo.get());
    }
    // 새로운 유저면 유저 기본 정보 DB에 등록한다.
    UserInfo newUserInfo = userService.saveUserInfo(naverUserInfo);

    return userService.login(isNewUSer, newUserInfo);
  }
}
