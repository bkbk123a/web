package com.example.web.controller;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.service.attend.AttendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attend")
@Slf4j
@RequiredArgsConstructor
public class AttendController {

  private final AttendService attendService;

  @GetMapping("/info")
  public AttendInfoDto.Response getUserAttendInfo() {
    return attendService.getUserAttendInfo();
  }

  @PostMapping("")
  public AttendDto.Response attend() {
    return attendService.attend();
  }
}
