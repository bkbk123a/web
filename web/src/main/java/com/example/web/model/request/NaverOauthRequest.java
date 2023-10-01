package com.example.web.model.request;

import com.example.web.model.enums.OauthType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@Getter
@NoArgsConstructor
public class NaverOauthRequest implements OauthCommonRequest{

    private String authorizationCode;
    private String state;

    @Override
    public OauthType getOauthType() {
        return OauthType.NAVER;
    }

    @Override
    public HashMap<String, String> makeHttpBody() {
        HashMap<String, String> body = new HashMap<>();
        body.put("code", authorizationCode);
        body.put("state", state);
        return body;
    }
}
