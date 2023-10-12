package com.example.web.controller;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.service.oauth.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@Slf4j
@RequiredArgsConstructor
public class OauthConroller {

    private final OauthService oauthService;

    @GetMapping("/naver/login-callback")
    public OauthNaverLoginDto.Response oauthNaverLogin(
        @RequestParam(name = "code") String authorizetionCode,
        @RequestParam(name = "state") String state) {

        OauthNaverLoginDto.Request request = OauthNaverLoginDto.Request.builder()
            .authorizationCode(authorizetionCode)
            .state(state)
            .build();

        return oauthService.processNaverLogin(request);
    }

//    @PostMapping("kakao")
//    public OauthResponse oauthKakao(@RequestBody KakaoOauthRequest request) {
//        return oauthService.processOauth(request);
//    }
}
