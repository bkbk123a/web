package com.example.web.exception;

import lombok.Builder;

@Builder
public class EnumConvertException extends RuntimeException {

  private final String message;
}