package com.example.web.util.advice;

import com.example.web.model.exception.EnumConvertException;
import com.example.web.model.exception.ErrorException;
import com.example.web.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = ErrorException.class)
  public ResponseEntity<ExceptionResponse> handleErrorException(ErrorException exception) {

    int resultValue = exception.getResultValue();
    com.example.web.model.enums.ErrorException enumException = getEnumExceptionOrElseThrow(resultValue);

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .resultValue(resultValue)
        .resultMsg(enumException.getResultMsg())
        .build();

    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }

  private com.example.web.model.enums.ErrorException getEnumExceptionOrElseThrow(int resultValue) {
    com.example.web.model.enums.ErrorException enumException = com.example.web.model.enums.ErrorException.ofErrorException(resultValue);

    if (enumException == com.example.web.model.enums.ErrorException.NOT_DEFINED) {
      throw new IllegalArgumentException();
    }

    return enumException;
  }

  @ExceptionHandler(value = EnumConvertException.class)
  public ResponseEntity<ExceptionResponse> handleEnumConvertException(EnumConvertException exception) {

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .resultValue(-1)
        .resultMsg(exception.getResultMsg())
        .build();

    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }
}
