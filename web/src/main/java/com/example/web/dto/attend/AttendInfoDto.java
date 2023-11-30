package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
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

    @Schema(description = "서버 기준 시간")
    @JsonProperty("ServerTime")
    private OffsetDateTime now;

    @Schema(description = "진행 중인 출석 정보")
    @Builder.Default
    @JsonProperty("NowAttendTimes")
    private List<AttendTime> nowAttendTimes = new ArrayList<>();

    @Schema(description = "유저 출석 정보")
    @Builder.Default
    @JsonProperty("UserAttends")
    private List<UserAttend> userAttends = new ArrayList<>();

  }
}
