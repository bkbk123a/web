package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.entity.user.UserMoneyLog;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class AttendDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  public static class Dto {

    private OffsetDateTime now;
    private Long userIndex;
    private UserInfo userInfo;
    private List<UserAttend> userAttends;
    private List<AttendTime> nowAttendTimes; // 진행 중인 출석 정보들
    private UserMoneyLog userMoneyLog;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "유저 출석 정보")
    @JsonProperty("UserAttends")
    @Builder.Default
    private List<UserAttend> userAttends = new ArrayList<>();

    @Schema(description = "출석 후 돈")
    @JsonProperty("Money")
    private long money;
  }
}
