package com.example.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Slf4j
public class CommonUtil {

  public static final String LOG_ID = "logId";
  public static final String REQUEST_TIME = "requestTime";

  public static String convertObjectToString(Object obj) {
    //Java 객체 간의 변환 담당 클래스
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("convertObjectToString Error Occurred", e);
    }
    throw new RuntimeException();
  }

  public static Map<String, Object> convertJsonStringToMap(String jsonString) {
    //Java 객체 간의 변환 담당 클래스
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(jsonString, Map.class);
    } catch (JsonProcessingException e) {
      log.error("convertObjectToString Error Occurred", e);
    }
    throw new RuntimeException();
  }

  public static Long getLocalDateTimeDifferenceMilliSec(LocalDateTime fromTime, LocalDateTime toTime) {
    return fromTime.until(toTime, ChronoUnit.MILLIS);
  }

  public static OffsetDateTime getOffsetDateTimeFromLocalDateTime(LocalDateTime now) {
    return now.atOffset(ZoneOffset.UTC);
  }
}
