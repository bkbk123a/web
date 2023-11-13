package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class AttendInfoDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "유저 출석 정보")
    @Builder.Default
    @JsonProperty("UserAttends")
    private List<UserAttend> userAttends = new ArrayList<>();

  }
}
