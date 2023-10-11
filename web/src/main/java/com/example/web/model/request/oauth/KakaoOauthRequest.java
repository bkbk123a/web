package com.example.web.model.request.oauth;

import com.example.web.model.enums.OauthType;
import com.example.web.model.request.oauth.OauthCommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoOauthRequest implements OauthCommonRequest {

    private String authorizationCode;

    @Override
    public OauthType getOauthType() {
        return OauthType.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeHttpBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}
