package com.example.web.service.event;

import com.example.web.jpa.entity.event.EventTime;
import com.example.web.model.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventTimeService eventTimeService;

  public EventTime getEventTime(LocalDateTime now, EventType eventType) {
    return eventTimeService.getEventTime(now, eventType);
  }
}
