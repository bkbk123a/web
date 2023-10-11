package com.example.web.service.attend;

import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.repository.user.UserAttendRepository;
import com.example.web.model.enums.AttendType;
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

  public void processAttend(Long userIndex, LocalDateTime now) {
    List<UserAttend> userAttends = getUserAttends(userIndex);
    List<AttendTime> attendTimes = attendTimeService.getAttendTimes(now);

    for (UserAttend attend : userAttends) {
      if (attend.getAttendType() == AttendType.DAILY_ATTEND) {
        processDailyAttend(now, attend, attendTimes);
      }
    }

    userAttendRepository.saveAll(userAttends);
  }

  private void processDailyAttend(LocalDateTime now, UserAttend userAttend, List<AttendTime> attendTimes) {
    if (!isDailyAttendPossible(now, userAttend, attendTimes)) {
      return;
    }

    userAttend.setLastAttendAt(now);
    userAttend.increaseAttendCount();
  }

  private boolean isDailyAttendPossible(LocalDateTime now, UserAttend userAttend, List<AttendTime> attendTimes) {
    boolean isTodayFirstAttend = !userAttend.getLastAttendAt().toLocalDate().equals(now.toLocalDate());

    if (!isTodayFirstAttend) {
      return false;
    }

    AttendTime attendTime = attendTimes.stream()
        .filter(e -> e.getAttendType() == AttendType.DAILY_ATTEND)
        .findFirst()
        .orElse(null);

    return attendTime != null;
  }
}
