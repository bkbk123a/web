package com.example.web.service.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.repository.attend.AttendRepository;
import com.example.web.model.enums.AttendType;
import jakarta.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendTimeService {

  private final AttendRepository attendRepository;

  @PostConstruct
  private void init() {
    List<AttendTime> attendTimes = new ArrayList<>();
    OffsetDateTime now = OffsetDateTime.now();

    attendTimes.add(getNewAttendTime(1, AttendType.DAILY_ATTEND,
        "1일 출석", now.minusDays(10), now.plusDays(10)));

    attendRepository.saveAll(attendTimes);
  }

  private AttendTime getNewAttendTime(int attendIndex, AttendType attendType,
      String description, OffsetDateTime startTime, OffsetDateTime endTime) {
    return AttendTime.builder()
        .attendIndex(attendIndex)
        .attendType(attendType)
        .description(description)
        .startTime(startTime)
        .endTime(endTime)
        .build();
  }

  /** 진행 중인 출석부 시간 조회
   *
   * @param now 현재 시간
   * @return 진행 중인 출석부 시간
   */
  public List<AttendTime> getNowAttendTimes(OffsetDateTime now) {
    return attendRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(now, now);
  }
}
