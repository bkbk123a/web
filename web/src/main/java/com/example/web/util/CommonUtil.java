package com.example.web.util;

import com.example.web.model.exception.CustomErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Slf4j
public class CommonUtil {

  public static final String LOG_ID = "logId";
  public static final String REQUEST_TIME = "requestTime";

  public static String convertObjectToString(Object obj) {
    //Java 객체 간의 변환 담당 클래스
    ObjectMapper objectMapper = new ObjectMapper();
    // LocalDateTime 타입의 객체 직렬화/역직렬화 지원
    objectMapper.registerModule(new JavaTimeModule());
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("", e);
    }

    throw CustomErrorException.builder().resultValue(1).build();
  }

  public static Long getLocalDateTimeDifferenceMilliSec(LocalDateTime fromTime, LocalDateTime toTime) {
    return fromTime.until(toTime, ChronoUnit.MILLIS);
  }

  public static OffsetDateTime getOffsetDateTimeFromLocalDateTime(LocalDateTime now) {
    return now.atOffset(ZoneOffset.UTC);
  }
}
