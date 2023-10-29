package com.example.web.service.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.repository.attend.AttendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendTimeService {

  private final AttendRepository eventRepository;

  /** 현재 진행중인 출석부 시간 조회
   *
   * @param now 현재 시간
   * @return 진행중인 출석부 시간
   */
  public List<AttendTime> getAttendTimes(LocalDateTime now) {
    return eventRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(now, now);
  }
}
