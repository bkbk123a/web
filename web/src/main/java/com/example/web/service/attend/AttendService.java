package com.example.web.service.attend;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.repository.user.UserAttendRepository;
import com.example.web.model.enums.AttendType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService {

  private final AttendTimeService attendTimeService;
  private final UserAttendRepository userAttendRepository;

  public AttendInfoDto.Response getUserAttendInfo(AttendInfoDto.Request request) {

    List<UserAttend> userAttends = getUserAttends(request.getUserIndex());

    return AttendInfoDto.Response.
        builder()
        .userAttends(userAttends)
        .build();
  }

  private List<UserAttend> getUserAttends(Long userIndex) {
    return userAttendRepository.findByUserIndex(userIndex);
  }

  @Transactional
  public AttendDto.Response attend(AttendDto.Request request) {
    AttendDto.Dto dto = getAttendDto(request);

    List<UserAttend> userAttends = dto.getUserAttends();

    for (UserAttend attend : userAttends) {
      // 현재는 1일 출석만 있다.
      if (attend.getAttendType() == AttendType.DAILY_ATTEND) {
        processDailyAttend(attend, dto);
      }
    }

    userAttendRepository.saveAll(userAttends);

    return AttendDto.Response
        .builder()
        .userAttends(dto.getUserAttends())
        .build();
  }

  private AttendDto.Dto getAttendDto(AttendDto.Request request) {
    Long userIndex = request.getUserIndex();
    LocalDateTime now = LocalDateTime.now();

    return AttendDto.Dto.builder()
        .userIndex(userIndex)
        .now(now)
        .userAttends(getUserAttends(userIndex))
        .attendTimes(attendTimeService.getAttendTimes(now))
        .build();
  }

  private void processDailyAttend(UserAttend userAttend, AttendDto.Dto dto) {
    if (!isDailyAttendCondition(userAttend, dto)) {
      return;
    }

    userAttend.setLastAttendAt(dto.getNow());
    userAttend.increaseAttendCount();
  }

  /**
   * 1일 1회 출석 조건 만족 판단
   * (유저가 오늘 처음 출석) && (진행 중인 출석의 AttendType = DAILY_ATTEND) 이면 출석 조건 만족
   *
   * @param userAttend 유저 출석 정보(AttendType = DAILY_ATTEND)
   * @param dto        dto
   * @return           1일 1회 출석 조건 여부(true : 조건 만족, false : 조건 불만족)
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
}
