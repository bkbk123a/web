package com.example.web.model.enums;

import com.example.web.model.exception.CustomErrorException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MoneyLogType {
  PRODUCT(1, "상품 관련(구매)"),
  ATTEND(2, "출석 관련");

  @JsonValue
  private final int type;
  private final String description;

  MoneyLogType(int type, String description) {
    this.type = type;
    this.description = description;
  }

  public static MoneyLogType ofMoneyLogType(int type) {
    return Arrays.stream(MoneyLogType.values())
        .filter(v -> v.getType() == type)
        .findAny()
        .orElseThrow(() -> CustomErrorException.builder().resultValue(2).build());
  }
}
