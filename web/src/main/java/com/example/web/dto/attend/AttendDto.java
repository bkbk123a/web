package com.example.web.dto.attend;

import com.example.web.jpa.entity.attend.AttendTime;
import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private List<UserAttend> userAttends;
    private List<AttendTime> attendTimes;
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
