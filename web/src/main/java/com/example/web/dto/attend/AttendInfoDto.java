package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.model.request.CommonRequest;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class AttendInfoDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @EqualsAndHashCode(callSuper = true)
  public static class Request extends CommonRequest {

  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Builder.Default
    @JsonProperty("UserAttends")
    private List<UserAttend> userAttends = new ArrayList<>();

  }
}
