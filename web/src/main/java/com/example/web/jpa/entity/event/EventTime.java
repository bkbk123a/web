package com.example.web.jpa.entity.event;

import com.example.web.model.enums.EventType;
import com.example.web.model.enums.converter.EventTypeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "T_STATIC_EventTime")
public class EventTime {
  @Id
  @Column(name = "EventIndex")
  private Integer eventIndex;

  @Column(name = "EventType", nullable = false)
  @Convert(converter = EventTypeConverter.class)
  private EventType eventType;

  @Column(name = "Description", nullable = false)
  private String description;

  @Column(name = "StartTime", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "EndTime", nullable = false)
  private LocalDateTime endTime;
}
