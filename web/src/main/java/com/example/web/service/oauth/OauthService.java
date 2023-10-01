package com.example.web.service.oauth;

import com.example.web.model.request.KakaoOauthRequest;
import com.example.web.model.request.NaverOauthRequest;
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

    public OauthResponse processNaverOauth(NaverOauthRequest request) {

        String accessToken = naverOauthService.processAccessToken(request);

        return OauthResponse.builder().build();
    }

//    public OauthResponse processKakaoOauth(KakaoOauthRequest request) {
//
//        String accessToken = naverOauthService.processOauth(request);
//
//        return OauthResponse.builder().build();
//    }
}
