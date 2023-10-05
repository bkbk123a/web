package com.example.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CommonUtil {

  public static final String LOG_ID = "logId";

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
}

// ObjectMapper 클래스는 Jackson 라이브러리에서 JSON 데이터와 Java 객체 간의 변환을 담당하는 클래스
