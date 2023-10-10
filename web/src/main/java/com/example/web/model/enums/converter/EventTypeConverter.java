package com.example.web.model.enums.converter;

import com.example.web.model.enums.EventType;
import jakarta.persistence.AttributeConverter;

public class EventTypeConverter implements AttributeConverter<EventType, Integer> {
  @Override
  public Integer convertToDatabaseColumn(EventType attribute) {
    return attribute.getType();
  }

  @Override
  public EventType convertToEntityAttribute(Integer dbData) {
    return EventType.ofEventType(dbData);
  }
}