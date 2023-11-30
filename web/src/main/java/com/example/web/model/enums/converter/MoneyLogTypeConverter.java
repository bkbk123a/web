package com.example.web.model.enums.converter;

import com.example.web.model.enums.MoneyLogType;
import jakarta.persistence.AttributeConverter;

public class MoneyLogTypeConverter implements AttributeConverter<MoneyLogType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(MoneyLogType attribute)  {
    return attribute.getType();
  }

  @Override
  public MoneyLogType convertToEntityAttribute(Integer dbData) {
    return MoneyLogType.ofMoneyLogType(dbData);
  }
}
