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

  public List<AttendTime> getAttendTimes(LocalDateTime now) {
    return eventRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(now, now);
  }
}
