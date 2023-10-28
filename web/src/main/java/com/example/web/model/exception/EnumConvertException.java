package com.example.web.model.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnumConvertException extends RuntimeException {

  private final String resultMsg;
}