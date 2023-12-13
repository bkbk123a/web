package com.example.web.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CustomErrorException {
  // CustomExceptionHandler 에서 처리된다.
  NOT_DEFINED(-1, "정의되지 않았습니다."),
  OBJECT_CONVERT_FAILED(1, "Object 변환 실패입니다."),
  ENUM_CONVERT_FAILED(2, "Enum 변환 실패입니다."),
  EXTERNAL_API_RESPONSE_FAILED(3, "외부 API 호출에 대한 응답 실패입니다."),
  NONE_SESSION_INFO(4, "세션 정보가 없습니다."),
  // 1000 : 유저 정보 관련
  NOT_EXIST_USER_INFO(1000, "유저 정보가 없습니다."),
  NOT_ENOUGH_MONEY(1001, "돈이 부족합니다."),
  // 1010 : 상품 관련
  NOT_EXIST_PRODUCT_INFO(1010, "상품 정보가 없습니다"),
  NOT_ENOUGH_PRODUCT_QUANTITY(1011, "남아있는 상품 수량이 부족합니다."),
  // 1020 : 출석 관련
  ALREADY_DAILY_ATTEND(1020, "이미 1일 1회 출석을 하였습니다."),
  // 1030 : 게시글 관련
  NOT_EXIST_ARTICLE(1030, "존재하지 않는 게시글입니다.");

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
