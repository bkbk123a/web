package com.example.web.dto.user;

import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class UserInfoDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "유저 정보")
    @JsonProperty("UserInfo")
    private UserInfo userInfo;

  }
}
