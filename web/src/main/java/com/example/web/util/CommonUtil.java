package com.example.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class CommonUtil {

  public static String convertObjectToString(Object obj) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("convertObjectToString Error Occurred", e);
    }
    throw new RuntimeException();
  }

  public static Map<String, Object> convertJsonStringToMap(String jsonString) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(jsonString, Map.class);
    } catch (JsonProcessingException e) {
      log.error("convertObjectToString Error Occurred", e);
    }
    throw new RuntimeException();
  }

  public static String convertTimeToJsonString(LocalDateTime time) {
    ObjectMapper objectMapper = new ObjectMapper();

    // JavaTimeModule 을 ObjectMapper 에 등록 (날짜 및 시간 직렬화 위한 모듈)
    objectMapper.registerModule(new JavaTimeModule());

    try {
      return objectMapper.writeValueAsString(time);
    } catch (JsonProcessingException e) {
      log.error("convertTimeToJsonString Error Occurred", e);
    }
    throw new RuntimeException();
  }
}

// ObjectMapper 클래스는 Jackson 라이브러리에서 JSON 데이터와 Java 객체 간의 변환을 담당하는 클래스
