package com.example.web.controller;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.service.attend.AttendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attend")
@Slf4j
@RequiredArgsConstructor
public class AttendController {

  private final AttendService attendService;

  @Operation(
      summary = "유저 출석 정보",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\",\"UserAttends\":[]}")},
              schema = @Schema(
                  implementation = AttendInfoDto.Response.class))))
  @GetMapping("/info")
  public AttendInfoDto.Response getUserAttendInfo() {
    return attendService.getUserAttendInfo();
  }


  @Operation(
      summary = "유저 출석",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\",\"UserAttends\":[],\"Money\":10000}")},
              schema = @Schema(
                  implementation = AttendInfoDto.Response.class))))
  @PostMapping()
  public AttendDto.Response attend() {
    return attendService.attend();
  }
}
