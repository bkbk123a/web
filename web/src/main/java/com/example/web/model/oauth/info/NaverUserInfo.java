package com.example.web.model.oauth.info;

import com.example.web.model.enums.OauthType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverUserInfo {

    @JsonProperty("response")
    private UserInfo userInfo;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class UserInfo {
        private String email;
        private String nickname;
    }

    private OauthType oauthType = OauthType.NAVER;
}
