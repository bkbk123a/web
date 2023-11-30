package com.example.web.service.attend;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.entity.user.UserMoneyLog;
import com.example.web.jpa.repository.attend.UserAttendRepository;
import com.example.web.model.enums.AttendType;
import com.example.web.model.exception.CustomErrorException;
import com.example.web.service.ServiceBase;
import com.example.web.service.user.UserService;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AttendService extends ServiceBase {

  private final UserService userService;
  private final AttendTimeService attendTimeService;
  private final UserAttendRepository userAttendRepository;

  public AttendInfoDto.Response getAttendInfo() {

    OffsetDateTime now = OffsetDateTime.now();

    List<AttendTime> nowAttendTimes = attendTimeService.getNowAttendTimes(now);

    List<UserAttend> userAttends = getUserAttends(getUserIndex());

    return AttendInfoDto.Response.
        builder()
        .now(now)
        .nowAttendTimes(nowAttendTimes)
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
    OffsetDateTime now = OffsetDateTime.now();

    return AttendDto.Dto.builder()
        .userIndex(userIndex)
        .now(now)
        .userInfo(userInfo)
        .userAttends(getUserAttends(userIndex))
        .nowAttendTimes(attendTimeService.getNowAttendTimes(now))
        .build();
  }

  /**
   * 진행 중인 출석 종류별 출석 처리
   * 현재는 1일 1회 출석만 있다.
   *
   * @param dto
   */
  private void processAttend(AttendDto.Dto dto) {
    List<AttendTime> nowAttendTimes = dto.getNowAttendTimes();
    for (AttendTime nowAttendTime : nowAttendTimes) {
      // 현재는 1일 출석만 있음.
      if (nowAttendTime.getAttendType() == AttendType.DAILY_ATTEND) {
        UserAttend userAttend = getUserDailyAttend(dto);

        processDailyAttend(userAttend, dto);
      }
    }
  }

  /**
   * 유저의 1일 1회 출석 정보 획득
   * dto 에 1일 1회 출석 정보 조회 후 없으면 기본 정보 생성
   *
   * @param dto dto
   * @return 유저의 1일 1회 출석 정보
   */
  private UserAttend getUserDailyAttend(AttendDto.Dto dto) {
    return dto.getUserAttends().stream()
        .filter(f -> f.getAttendType() == AttendType.DAILY_ATTEND)
        .findFirst()
        .orElseGet(() -> UserAttend.builder()
            .userIndex(dto.getUserIndex())
            .attendType(AttendType.DAILY_ATTEND)
            .build());
  }

  /**
   * 1일 1회 출석 처리
   * 1일 1회 출석인 경우 진행
   * 2000원 지급, 출석 횟수 증가, 마지막 출석 시간 업뎃
   *
   * @param userAttend 유저 출석 정보
   * @param dto        dto
   */
  private void processDailyAttend(UserAttend userAttend, AttendDto.Dto dto) {
    checkDailyAttendCondition(userAttend, dto.getNow());

    final int dailyAttendRewardMoney = 2000;

    UserInfo userInfo = dto.getUserInfo();
    userInfo.addMoney((long) dailyAttendRewardMoney);

    long afterAttendMoney = userInfo.getMoney();

    UserMoneyLog userMoneyLog = UserMoneyLog.builder()
        .userIndex(userInfo.getUserIndex())
        .beforeMoney(afterAttendMoney - dailyAttendRewardMoney)
        .afterMoney(afterAttendMoney)
        .build();

    dto.setUserMoneyLog(userMoneyLog);
    // 영속성 컨텍스트에 관리중인 userAttends 에 add
    if (userAttend.getAttendCount() == 0) {
      dto.getUserAttends().add(userAttend);
    }

    userAttend.increaseAttendCount();

    userAttend.setLastAttendAt(dto.getNow());
  }

  /**
   * 1일 1회 출석 조건 만족 판단
   * 유저는 오늘 처음 출석 여부로 판단
   *
   * @param userAttend 유저 출석 정보(AttendType = DAILY_ATTEND)
   * @param now        현재 시간
   * @return 1일 1회 출석 조건 여부(true : 조건 만족, false : 조건 불만족)
   */
  private void checkDailyAttendCondition(UserAttend userAttend, OffsetDateTime now) {
    OffsetDateTime userLastAttendAt = userAttend.getLastAttendAt();
    // 한번도 출석한 적 없으면 출석 가능
    if (userLastAttendAt == null) {
      return;
    }
    // 유저의 마지막 출석 날짜(년,월,일)가 현재 시간의 날짜와 다르면 오늘 처음 출석)
    boolean isTodayFirstAttend = !userLastAttendAt.toLocalDate()
        .equals(now.toLocalDate());
    // 현재는 출석이 1일 1회 뿐이여서 throw 로 처리.
    // 추후 다른 종류의 출석이 생기면 return false 로 처리
    if (!isTodayFirstAttend) {
      throw CustomErrorException.builder().resultValue(10200).build();
    }
  }

  private void saveAttendInfo(AttendDto.Dto dto) {
    if (dto.getUserMoneyLog() != null) {
      userService.saveUserMoney(dto.getUserInfo(), dto.getUserMoneyLog());
    }
    userAttendRepository.saveAll(dto.getUserAttends());
  }
}
