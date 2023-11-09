package com.example.web.model.enums;

import com.example.web.model.exception.CustomErrorException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductType {
  TOP(1, "상의"),
  PANTS(2, "하의"),
  SHOES(3, "신발");

  @JsonValue
  private final int type;
  private final String description;

  ProductType(int type, String description) {
    this.type = type;
    this.description = description;
  }

  public static ProductType ofProductType(int productType) {
    return Arrays.stream(ProductType.values())
        .filter(v -> v.getType() == productType)
        .findAny()
        .orElseThrow(() -> CustomErrorException.builder().resultValue(2).build());
  }
}
