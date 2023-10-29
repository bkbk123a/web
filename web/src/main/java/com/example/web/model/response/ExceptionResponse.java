package com.example.web.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExceptionResponse {

  @JsonProperty("ResultValue")
  private int resultValue;

  @JsonProperty("ResultMsg")
  private String resultMsg;

  @JsonProperty("StackTrace")
  private String stackTrace;
}
