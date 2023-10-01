package com.example.web.model.request;

import com.example.web.model.enums.OauthType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor
public class KakaoOauthRequest implements OauthCommonRequest{

    private String authorizationCode;

    @Override
    public OauthType getOauthType() {
        return OauthType.KAKAO;
    }

    @Override
    public HashMap<String, String> makeHttpBody() {
        HashMap<String, String> body = new HashMap<>();
        body.put("code", authorizationCode);
        return body;
    }
}
