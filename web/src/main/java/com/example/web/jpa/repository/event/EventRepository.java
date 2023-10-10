package com.example.web.jpa.repository.event;

import com.example.web.jpa.entity.event.EventTime;
import com.example.web.model.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventTime, Integer> {

  /**
   * 현재 진행 중인 특정 이벤트 타입의 엔티티 조회
   * 진행중 : (시작 시간 <= 현재 시간 <= 종료 시간)
   *
   * @param now1      현재시간
   * @param now2      현재시간
   * @param eventType 이벤트 타입
   * @return          진행중인 이벤트
   */
  Optional<EventTime> findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndEventType(
      LocalDateTime now1, LocalDateTime now2, EventType eventType
  );
}
