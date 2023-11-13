package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  public static class Dto {

    private LocalDateTime now;
    private Long userIndex;
    private UserInfo userInfo;
    private List<UserAttend> userAttends;
    private List<AttendTime> attendTimes; // 진행중인 출석부 시간
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
