package com.example.web.dto.user;

import com.example.web.jpa.entity.user.UserMoneyLog;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class UserMoneyLogInfoDto {

  @Getter
  @SuperBuilder
  public static class Dto {
    private long userIndex;
    private Integer logType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {
    @Schema(description = "유저의 돈 변화 로그")
    @Builder.Default
    @JsonProperty("UserMoneyLogs")
    private List<UserMoneyLog> userMoneyLogs = new ArrayList<>();
  }
}
