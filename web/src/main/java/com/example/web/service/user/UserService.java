package com.example.web.service.user;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.user.UserRepository;
import com.example.web.model.oauth.info.OauthUserInfo;
import com.example.web.util.token.AuthTokenGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.web.util.CommonUtil.getOffsetDateTimeFromLocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final AuthTokenGenerator authTokenGenerator;

  public Optional<UserInfo> getUserInfo(String emailAddress) {
    return userRepository.findByEmailAddress(emailAddress);
  }

  public OauthNaverLoginDto.Response login(boolean isNewUser, @NonNull UserInfo userInfo) {
    LocalDateTime now = LocalDateTime.now();

    userInfo.setLastLoginAt(now);
    saveUserInfo(userInfo);

     String accessToken = authTokenGenerator.generateToken(userInfo.getUserIndex());

    return OauthNaverLoginDto.Response.builder()
        .isNewUser(isNewUser)
        .serverTime(getOffsetDateTimeFromLocalDateTime(now).toEpochSecond())
        .accessToken(accessToken)
        .build();
  }

  public UserInfo saveUserInfo(OauthUserInfo oauthUserInfo) {
    UserInfo userInfo = UserInfo.builder()
        .emailAddress(oauthUserInfo.getEmailAddress())
        .nickName(oauthUserInfo.getNickName())
        .build();

    return saveUserInfo(userInfo);
  }

  private UserInfo saveUserInfo(UserInfo userInfo) {
    return userRepository.save(userInfo);
  }
}
