package com.example.web.controller;

import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @IgnoreAuth
  @GetMapping
  public String login(){
    return loginService.getRedirectUrl();
  }
}
