package com.example.web.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonRequest {

  @JsonProperty("UserIndex")
  public Long userIndex;
}
