package com.example.web.controller;

import com.example.web.dto.user.UserInfoDto;
import com.example.web.dto.user.UserMoneyLogInfoDto;
import com.example.web.model.enums.MoneyLogType;
import com.example.web.model.enums.converter.MoneyLogTypeConverter;
import com.example.web.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Convert;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user", description = "유저 관련")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @Operation(
      summary = "유저 정보 조회",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\"," +
                          "\"UserInfo\":{\"userIndex\":1," +
                          "\"emailAddress\":\"qjarud32@naver.com\"," +
                          "\"nickName\":\"김범경\",\"createdAt\":\"2023-11-13T18:39:02.09767\"," +
                          "\"money\":10000,\"lastLoginAt\":\"2023-11-13T18:39:02.10481\"}}")},
              schema = @Schema(
                  implementation = UserInfoDto.Response.class))))
  @GetMapping("/info")
  public UserInfoDto.Response getUseInfo() {
    return userService.getUserInfo();
  }

  @Operation(
      summary = "유저 돈 변화 로그 조회",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\","
                          + "\"UserMoneyLogs\":[{\"logIndex\":1,\"logType\":1,"
                          + "\"userIndex\":1,\"beforeMoney\":1,\"afterMoney\":2,"
                          + "\"createdAt\":\"2023-12-01T01:03:43.031Z\"}]}")},
              schema = @Schema(
                  implementation = UserMoneyLogInfoDto.Response.class))))
  @GetMapping("/money/log-info")
  public UserMoneyLogInfoDto.Response getUserMoneyLogInfo(
      @Parameter(example = "1", description = "돈 관련 로그 타입(1:상품 관련, 2:출석 관련), null 허용")
      @RequestParam(name = "MoneyLogType", required = false)
      Integer logType,
      @Parameter(example = "2023-01-01T00:00:00", description = "조회 최소 시간, null 허용")
      @RequestParam(name = "StartTime", required = false)
      LocalDateTime startTime,
      @Parameter(example = "2023-12-31T00:00:00", description = "조회 최대 시간, null 허용")
      @RequestParam(name = "EndTime", required = false)
      LocalDateTime endTime) {
    return userService.getUserMoneyLogInfo(logType, startTime, endTime);
  }
}
