package com.example.web.service.user;

import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.user.UserRepository;
import com.example.web.model.oauth.JwtUser;
import com.example.web.model.oauth.info.OauthUserInfo;
import com.example.web.model.response.OauthResponse;
import com.example.web.util.container.SessionContainer;
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

  public Optional<UserInfo> getUserInfo(String emailAddress) {
    return userRepository.findByEmailAddress(emailAddress);
  }

  public UserInfo getUserInfo(Long userIndex) {
    return userRepository.findById(userIndex)
        .orElseGet(() -> UserInfo.builder()
            .userIndex(-1L)
            .build());
  }

  public OauthResponse login(boolean isNewUser, @NonNull UserInfo userInfo) {
    LocalDateTime now = LocalDateTime.now();

    userInfo.setLastLoginAt(now);
    saveUserInfo(userInfo);

    JwtUser jwtUser = JwtUser.builder()
        .userIndex(userInfo.getUserIndex())
        .expireLeftSec(7200)
        .build();

    SessionContainer.setSession(jwtUser);

    return OauthResponse.builder()
        .isNewUser(isNewUser)
        .serverTime(getOffsetDateTimeFromLocalDateTime(now).toEpochSecond())
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
