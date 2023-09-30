package com.example.web.controller;

import com.example.web.model.request.KakaoOauthRequest;
import com.example.web.model.request.NaverOauthRequest;
import com.example.web.model.response.OauthResponse;
import com.example.web.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/")
@Slf4j
@RequiredArgsConstructor
public class OauthConroller {

    private final OauthService oauthService;

    @PostMapping("/naver")
    public OauthResponse oauthNaver(@RequestBody NaverOauthRequest request) {
        return oauthService.oauth(request);
    }

    @PostMapping("/kakao")
    public OauthResponse oauthKakao(@RequestBody KakaoOauthRequest request) {
        return oauthService.oauth(request);
    }
}
