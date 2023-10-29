package com.example.web.util.advice;

import com.example.web.model.enums.CustomErrorException;
import com.example.web.model.exception.EnumConvertException;
import com.example.web.model.exception.ErrorException;
import com.example.web.model.response.ExceptionResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Runtime 도중 정의한 예외 상황 처리
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = ErrorException.class)
  public ResponseEntity<ExceptionResponse> handleErrorException(ErrorException exception) {

    int resultValue = exception.getResultValue();
    CustomErrorException enumException = getEnumExceptionOrElseThrow(resultValue);

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .resultValue(resultValue)
        .resultMsg(enumException.getResultMsg())
        .stackTrace(getStackTrace(exception))
        .build();

    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }

  private CustomErrorException getEnumExceptionOrElseThrow(int resultValue) {
    CustomErrorException enumException =
        CustomErrorException.ofErrorException(resultValue);

    if (enumException == CustomErrorException.NOT_DEFINED) {
      throw new IllegalArgumentException();
    }

    return enumException;
  }

  @ExceptionHandler(value = EnumConvertException.class)
  public ResponseEntity<ExceptionResponse> handleEnumConvertException(EnumConvertException exception) {

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .resultValue(-1)
        .resultMsg(exception.getResultMsg())
        .stackTrace(getStackTrace(exception))
        .build();

    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }

  private String getStackTrace(RuntimeException exception) {
    return ExceptionUtils.getStackTrace(exception);
  }
}
