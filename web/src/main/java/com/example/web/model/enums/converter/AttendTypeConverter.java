package com.example.web.model.enums.converter;

import com.example.web.model.enums.AttendType;
import jakarta.persistence.AttributeConverter;

public class AttendTypeConverter implements AttributeConverter<AttendType, Integer> {
  @Override
  public Integer convertToDatabaseColumn(AttendType attribute)  {
    return attribute.getType();
  }

  @Override
  public AttendType convertToEntityAttribute(Integer dbData) {
    return AttendType.ofAttendType(dbData);
  }
}
