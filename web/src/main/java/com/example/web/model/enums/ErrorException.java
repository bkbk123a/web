package com.example.web.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ErrorException {

  NOT_DEFINED(-1, "정의되지 않았습니다."),
  NONE_USER_INFO(10001, "유저 정보가 없습니다.");

  private final int resultValue;
  private final String resultMsg;

  ErrorException(int resultValue, String resultMsg) {
    this.resultValue = resultValue;
    this.resultMsg = resultMsg;
  }

  public static ErrorException ofErrorException(int resultValue) {
    return Arrays.stream(ErrorException.values())
        .filter(v -> v.getResultValue() == resultValue)
        .findAny()
        .orElseGet(() -> ErrorException.NOT_DEFINED);
  }
}
