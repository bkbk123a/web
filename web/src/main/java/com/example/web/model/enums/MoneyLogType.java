package com.example.web.model.enums;

import com.example.web.model.exception.CustomErrorException;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MoneyLogType {
  PRODUCT_BUY_MONEY_USE(1, "상품 구매로 돈 사용"),
  ATTEND_MONEY_GET(100, "출석 돈 획득");

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
