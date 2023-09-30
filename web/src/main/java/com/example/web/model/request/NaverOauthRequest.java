package com.example.web.model.request;

import com.example.web.model.enums.OauthType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverOauthRequest implements OauthCommonRequest{

    private String authorizationCode;
    private String state;
    @Override
    public OauthType getOauthType() {
        return OauthType.NAVER;
    }
}
