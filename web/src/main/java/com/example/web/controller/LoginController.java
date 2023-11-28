package com.example.web.controller;

import com.example.web.dto.oauth.OauthNaverLoginDto;
import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.service.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "login", description = "로그인 관련")
@IgnoreAuth
@Controller
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @IgnoreAuth
  @Operation(
      summary = "외부 플랫폼 이용한 로그인",
      description = "localhost:8080을 이용해 로그인하세요. JWT 필요 없음",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200"))
  @PostMapping
  public String login() {
    return loginService.getRedirectUrl();
  }

  @IgnoreAuth
  @Operation(
      summary = "네이버 연동 로그인 콜백",
      description = "JWT 필요 없음",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\"," +
                          "\"ServerTime\":1699901101,\"IsNewUser\":true}")},
              schema = @Schema(
                  implementation = OauthNaverLoginDto.Response.class))))
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
