package com.example.web.model.enums.converter;

import com.example.web.model.enums.AttendType;
import com.example.web.model.enums.ProductType;
import jakarta.persistence.AttributeConverter;

public class ProductTypeConverter implements AttributeConverter<ProductType, Integer> {
  @Override
  public Integer convertToDatabaseColumn(ProductType attribute)  {
    return attribute.getType();
  }

  @Override
  public ProductType convertToEntityAttribute(Integer dbData) {
    return ProductType.ofProductType(dbData);
  }
}
