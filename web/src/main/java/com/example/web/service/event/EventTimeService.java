package com.example.web.service.event;

import com.example.web.jpa.entity.event.EventTime;
import com.example.web.jpa.repository.event.EventRepository;
import com.example.web.model.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventTimeService {

  private final EventRepository eventRepository;

  public EventTime getEventTime(LocalDateTime now, EventType eventType) {
    return eventRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndEventType(
        now, now, eventType)
        .orElseGet(() -> EventTime.builder()
            .eventType(EventType.NONE)
            .build());
  }
}
