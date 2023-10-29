package com.example.web.util.advice;

import com.example.web.model.exception.CustomErrorException;
import com.example.web.model.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Runtime 도중 정의한 예외 상황 처리
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = CustomErrorException.class)
  public ResponseEntity<ExceptionResponse> handleCustomErrorException(CustomErrorException exception) {

    int resultValue = exception.getResultValue();
    com.example.web.model.enums.CustomErrorException enumException = getEnumExceptionOrElseThrow(resultValue);

    log.error("", exception);

    ExceptionResponse exceptionResponse = getExceptionResponse(enumException.getResultValue(),
        enumException.getResultMsg(), exception);

    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }

  private com.example.web.model.enums.CustomErrorException getEnumExceptionOrElseThrow(int resultValue) {
    com.example.web.model.enums.CustomErrorException enumException =
        com.example.web.model.enums.CustomErrorException.ofErrorException(resultValue);

    if (enumException == com.example.web.model.enums.CustomErrorException.NOT_DEFINED) {
      throw new IllegalArgumentException();
    }

    return enumException;
  }

  private ExceptionResponse getExceptionResponse(int resultValue, String resultMsg,
                                                 RuntimeException exception) {
    return ExceptionResponse.builder()
        .resultValue(resultValue)
        .resultMsg(resultMsg)
        .stackTrace(getStackTrace(exception))
        .build();
  }

  private String getStackTrace(RuntimeException exception) {
    return ExceptionUtils.getStackTrace(exception);
  }
}
