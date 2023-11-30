package com.example.web.jpa.repository.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import java.time.OffsetDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendRepository extends JpaRepository<AttendTime, Integer> {

  /**
   * 현재 진행 중인 출석 조회
   * 진행중 : (시작 시간 <= 현재 시간 <= 종료 시간)
   *
   * @param now1 현재 시간
   * @param now2 현재 시간
   * @return 진행 중인 출석
   */
  List<AttendTime> findByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(OffsetDateTime now1, OffsetDateTime now2);
}
