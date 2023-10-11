package com.example.web.model.request.oauth;

import com.example.web.model.enums.OauthType;
import com.example.web.model.request.oauth.OauthCommonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class NaverOauthRequest implements OauthCommonRequest {

    private String authorizationCode;
    private String state;

    @Override
    public OauthType getOauthType() {
        return OauthType.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makeHttpBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("state", state);
        return body;
    }
}
