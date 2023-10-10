package com.example.web.model.enums;

import com.example.web.exception.EnumConvertException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum EventType {
  NONE(0, "진행중인 이벤트 없음"),
  ATTEND(1, "출석 이벤트"),
  OLYMPIC(2, "올림픽 이벤트 이벤트");

  @JsonValue
  private final int type;
  private final String description;

  EventType(int type, String description) {
    this.type = type;
    this.description = description;
  }

   public static EventType ofEventType(int eventType) {
    return Arrays.stream(EventType.values())
        .filter(v -> v.getType() == eventType)
        .findAny()
        .orElseThrow(() -> EnumConvertException
            .builder()
            .message(String.format("EventType = [%d]가 존재하지 않습니다.", eventType))
            .build());
  }
}