package com.example.web.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OauthResponse {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;
}
