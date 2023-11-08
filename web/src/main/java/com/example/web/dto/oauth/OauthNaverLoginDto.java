package com.example.web.dto.oauth;

import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class OauthNaverLoginDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @SuperBuilder
  public static class Request {

    private String authorizationCode;
    private String state;

    public MultiValueMap<String, String> makeHttpBody() {
      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("code", authorizationCode);
      body.add("state", state);
      return body;
    }
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    // 서버 기준 시간 - unix timestamp
    @JsonProperty("ServerTime")
    private long serverTime;

    // 계정 생성 여부
    @JsonProperty("IsNewUser")
    private boolean isNewUser;
  }
}
