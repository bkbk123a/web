package com.example.web.service.oauth;

import com.example.web.model.request.OauthCommonRequest;
import com.example.web.model.response.OauthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final NaverOauthService naverOauthService;
    private final KakaoOauthService kakaoOauthService;

    public OauthResponse processOauth(OauthCommonRequest request) {

        return OauthResponse.builder().build();
    }
}
