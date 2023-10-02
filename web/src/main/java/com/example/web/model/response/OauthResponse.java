package com.example.web.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OauthResponse {

    // 서버 기준 시간 - unix timestamp
    private long serverTime;
    // 계정 생성 여부
    private boolean isNewUser;
}
