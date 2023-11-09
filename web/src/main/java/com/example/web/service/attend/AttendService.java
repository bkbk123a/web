package com.example.web.service.attend;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.user.UserAttendRepository;
import com.example.web.model.enums.AttendType;
import com.example.web.service.ServiceBase;
import com.example.web.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService extends ServiceBase {

  private final UserService userService;
  private final AttendTimeService attendTimeService;
  private final UserAttendRepository userAttendRepository;

  public AttendInfoDto.Response getUserAttendInfo() {
    List<UserAttend> userAttends = getUserAttends(getUserIndex());

    return AttendInfoDto.Response.
        builder()
        .userAttends(userAttends)
        .build();
  }

  private List<UserAttend> getUserAttends(Long userIndex) {
    return userAttendRepository.findByUserIndex(userIndex);
  }

  @Transactional
  public AttendDto.Response attend() {
    // 1. 필요한 정보 담긴 dto get
    AttendDto.Dto dto = getAttendDto();

    // 2. 출석 종류별 출석 진행
    processAttend(dto);

    // 3. DB에 저장
    saveAttendInfo(dto);

    return AttendDto.Response
        .builder()
        .money(dto.getUserInfo().getMoney())
        .userAttends(dto.getUserAttends())
        .build();
  }

  private AttendDto.Dto getAttendDto() {
    long userIndex = getUserIndex();
    UserInfo userInfo = userService.getUserInfoOrElseThrow(userIndex);
    LocalDateTime now = LocalDateTime.now();

    return AttendDto.Dto.builder()
        .userIndex(userIndex)
        .now(now)
        .userInfo(userInfo)
        .userAttends(getUserAttends(userIndex))
        .attendTimes(attendTimeService.getAttendTimes(now))
        .build();
  }

  /**
   * 출석 종류별 출석 처리
   * 현재는 1일 1회 출석만 있다.
   *
   * @param dto
   */
  private void processAttend(AttendDto.Dto dto) {
    List<UserAttend> userAttends = dto.getUserAttends();
    for (UserAttend attend : userAttends) {
      // 현재는 1일 출석
      if (attend.getAttendType() == AttendType.DAILY_ATTEND) {
        processDailyAttend(attend, dto);
      }
    }
  }

  /**
   * 1일 1회 출석 처리
   * 1일 1회 출석인 경우에만 진행한다
   * 2000원 지급, 출석횟수 증가, 마지막 출석시간 업뎃
   *
   * @param userAttend
   * @param dto
   */
  private void processDailyAttend(UserAttend userAttend, AttendDto.Dto dto) {
    if (!isDailyAttendCondition(userAttend, dto)) {
      return;
    }

    final int dailyAttendRewardMoney = 2000;

    UserInfo userInfo = dto.getUserInfo();
    userInfo.addMoney((long) dailyAttendRewardMoney);

    userAttend.increaseAttendCount();
    userAttend.setLastAttendAt(dto.getNow());
  }

  /**
   * 1일 1회 출석 조건 만족 판단
   * (유저가 오늘 처음 출석) && (진행 중인 출석의 AttendType = DAILY_ATTEND) 이면 출석 조건 만족
   *
   * @param userAttend 유저 출석 정보(AttendType = DAILY_ATTEND)
   * @param dto        dto
   * @return 1일 1회 출석 조건 여부(true : 조건 만족, false : 조건 불만족)
   */
  private boolean isDailyAttendCondition(UserAttend userAttend, AttendDto.Dto dto) {
    boolean isTodayFirstAttend = !userAttend.getLastAttendAt()
        .toLocalDate().equals(dto.getNow().toLocalDate());

    if (!isTodayFirstAttend) {
      return false;
    }

    AttendTime attendTime = dto.getAttendTimes().stream()
        .filter(e -> e.getAttendType() == AttendType.DAILY_ATTEND)
        .findFirst()
        .orElse(null);

    return attendTime != null;
  }

  private void saveAttendInfo(AttendDto.Dto dto) {
    userService.saveUserInfo(dto.getUserInfo());
    userAttendRepository.saveAll(dto.getUserAttends());
  }
}
