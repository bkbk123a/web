package com.example.web.service.oauth;

import com.example.web.jpa.entity.UserInfo;
import com.example.web.model.oauth.info.NaverUserInfo;
import com.example.web.model.request.NaverOauthRequest;
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

        NaverUserInfo naverUserInfo = naverOauthService.processUserInfo(accessToken);

        UserInfo userInfo = naverOauthService.getUserInfo(naverUserInfo);

        return OauthResponse.builder().build();
    }
//    public OauthResponse processKakaoOauth(KakaoOauthRequest request) {
//
//        String accessToken = naverOauthService.processOauth(request);
//
//        return OauthResponse.builder().build();
//    }
}
