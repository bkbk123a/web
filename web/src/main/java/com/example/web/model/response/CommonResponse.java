package com.example.web.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CommonResponse {

  @JsonProperty("ResultVal")
  private int resultVal;

  @JsonProperty("ResultMsg")
  @Builder.Default
  private String resultMsg = "Success";
}
