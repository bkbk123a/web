package com.example.web.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CustomErrorException {
  // CustomExceptionHandler 에서 처리된다.
  NOT_DEFINED(-1, "정의되지 않았습니다."),
  OBJECT_CONVERT_FAILED(1, "Object 변환 실패입니다."),
  ENUM_CONVERT_FAILED(2, "Enum 변환 실패입니다."),
  NONE_USER_INFO(10001, "유저 정보가 없습니다.");

  private final int resultValue;
  private final String resultMsg;

  CustomErrorException(int resultValue, String resultMsg) {
    this.resultValue = resultValue;
    this.resultMsg = resultMsg;
  }

  public static CustomErrorException ofErrorException(int resultValue) {
    return Arrays.stream(CustomErrorException.values())
        .filter(v -> v.getResultValue() == resultValue)
        .findAny()
        .orElseGet(() -> CustomErrorException.NOT_DEFINED);
  }
}
