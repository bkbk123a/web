package com.example.web.service.user;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.dto.user.UserInfoDto;
import com.example.web.dto.user.UserMoneyLogInfoDto;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.entity.user.UserMoneyLog;
import com.example.web.jpa.repository.user.UserMoneyLogRepository;
import com.example.web.jpa.repository.user.UserMoneyLogRepositorySupport;
import com.example.web.jpa.repository.user.UserRepository;
import com.example.web.model.exception.CustomErrorException;
import com.example.web.model.oauth.JwtUser;
import com.example.web.model.oauth.info.OauthUserInfo;
import com.example.web.service.ServiceBase;
import com.example.web.util.container.SessionContainer;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.web.util.CommonUtil.getOffsetDateTimeFromLocalDateTime;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService extends ServiceBase {

  private final UserRepository userRepository;
  private final UserMoneyLogRepository userMoneyLogRepository;
  private final UserMoneyLogRepositorySupport userMoneyLogRepositorySupport;

  public Optional<UserInfo> getUserInfo(String emailAddress) {
    return userRepository.findByEmailAddress(emailAddress);
  }

  @Transactional
  public OauthNaverLoginDto.Response login(boolean isNewUser, @NonNull UserInfo userInfo) {

    JwtUser jwtUser = JwtUser.builder().userIndex(userInfo.getUserIndex()).build();
    SessionContainer.setSession(jwtUser);

    LocalDateTime now = LocalDateTime.now();

    userInfo.setLastLoginAt(now);
    saveUserInfo(userInfo);

    return OauthNaverLoginDto.Response.builder()
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

  public UserInfo saveUserInfo(UserInfo userInfo) {
    return userRepository.save(userInfo);
  }

  public UserInfoDto.Response getUserInfo() {
    UserInfo userInfo = getUserInfoOrElseThrow(getUserIndex());

    return UserInfoDto.Response.builder()
        .userInfo(userInfo)
        .build();
  }

  public UserInfo getUserInfoOrElseThrow(long userIndex) {
    return userRepository.findById(userIndex)
        .orElseThrow(() -> CustomErrorException.builder().resultValue(1000).build());
  }

  public void checkEnoughMoney(long needMoney, UserInfo userInfo) {
    if (userInfo.getMoney() < needMoney) {
      throw CustomErrorException.builder().resultValue(1001).build();
    }
  }

  public void saveUserMoney(UserInfo userInfo, UserMoneyLog userMoneyLog) {
    userRepository.save(userInfo);
    userMoneyLogRepository.save(userMoneyLog);
  }

  public UserMoneyLogInfoDto.Response getUserMoneyLogInfo(Integer logType,
      LocalDateTime startTime, LocalDateTime endTime) {
    UserMoneyLogInfoDto.Dto dto = getDto(logType, startTime, endTime);

    return UserMoneyLogInfoDto.Response.builder()
        .userMoneyLogs(getUserMoneyLogs(dto))
        .build();
  }

  private UserMoneyLogInfoDto.Dto getDto(Integer logType,
      LocalDateTime startTime, LocalDateTime endTime) {
    return UserMoneyLogInfoDto.Dto.builder()
        .userIndex(getUserIndex())
        .logType(logType)
        .startTime(startTime)
        .endTime(endTime)
        .build();
  }

  private List<UserMoneyLog> getUserMoneyLogs(UserMoneyLogInfoDto.Dto dto) {
    return userMoneyLogRepositorySupport
        .getUserMoneyLogs(dto.getUserIndex(), dto.getLogType(),
            dto.getStartTime(), dto.getEndTime());
  }
}
