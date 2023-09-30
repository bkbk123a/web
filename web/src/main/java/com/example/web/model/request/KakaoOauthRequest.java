package com.example.web.model.request;

import com.example.web.model.enums.OauthType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoOauthRequest implements OauthCommonRequest{

    @Override
    public OauthType getOauthType() {
        return OauthType.KAKAO;
    }
}
