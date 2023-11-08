package com.example.web.controller;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@IgnoreAuth
@Controller
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @GetMapping
  public String login() {
    return loginService.getRedirectUrl();
  }

  @GetMapping("/naver-callback")
  public @ResponseBody OauthNaverLoginDto.Response oauthNaverLogin(
      @RequestParam(name = "code") String authorizetionCode,
      @RequestParam(name = "state") String state) {

    OauthNaverLoginDto.Request request = OauthNaverLoginDto.Request.builder()
        .authorizationCode(authorizetionCode)
        .state(state)
        .build();

    return loginService.processNaverLogin(request);
  }
}
