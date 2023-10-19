package com.example.web.controller;

import com.example.web.dto.user.UserInfoDto;
import com.example.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/info")
  public UserInfoDto.Response getUserAttendInfo() {
    return userService.getUserInfo();
  }
}
