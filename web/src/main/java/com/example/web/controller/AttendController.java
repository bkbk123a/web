package com.example.web.controller;

import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.service.attend.AttendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attend")
@Slf4j
@RequiredArgsConstructor
public class AttendController {

  private final AttendService attendService;

  @PostMapping("/info")
  public AttendInfoDto.Response getUserAttendInfo(@RequestBody AttendInfoDto.Request request) {
    return attendService.getUserAttendInfo(request);
  }
}
