package com.example.web.model.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorException extends RuntimeException{

  private final int resultValue;
}
