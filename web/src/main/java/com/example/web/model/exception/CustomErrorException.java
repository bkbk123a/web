package com.example.web.model.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomErrorException extends RuntimeException {
  // enum CustomExceptionHandler 참고
  private final int resultValue;
}
