package com.example.web.model.enums;

import com.example.web.model.exception.CustomErrorException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum AttendType {
  NONE(0, "출석 없음"),
  DAILY_ATTEND(1, "일일 출석");

  @JsonValue
  private final int type;
  private final String description;

  AttendType(int type, String description) {
    this.type = type;
    this.description = description;
  }

   public static AttendType ofAttendType(int attendType) {
    return Arrays.stream(AttendType.values())
        .filter(v -> v.getType() == attendType)
        .findAny()
        .orElseThrow(() -> CustomErrorException.builder().resultValue(2).build());
  }
}
