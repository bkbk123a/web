package com.example.web.controller;

import com.example.web.dto.attend.AttendDto;
import com.example.web.dto.attend.AttendInfoDto;
import com.example.web.service.attend.AttendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "attend", description = "출석 관련")
@RestController
@RequestMapping("/attend")
@Slf4j
@RequiredArgsConstructor
public class AttendController {

  private final AttendService attendService;

  @Operation(
      summary = "출석 정보",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\","
                          + "\"ServerTime\":\"2023-11-30T10:54:55.8396075+09:00\","
                          + "\"NowAttendTimes\":[{\"attendIndex\":1,\"attendType\":1,"
                          + "\"description\":\"1일 출석\","
                          + "\"startTime\":\"2023-11-20T10:53:54.438412+09:00\","
                          + "\"endTime\":\"2023-12-10T10:53:54.438412+09:00\"}],"
                          + "\"UserAttends\":[]}")},
              schema = @Schema(
                  implementation = AttendInfoDto.Response.class))))
  @GetMapping("/info")
  public AttendInfoDto.Response getAttendInfo() {
    return attendService.getAttendInfo();
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
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\","
                          + "\"UserAttends\":[{\"userIndex\":1,\"attendType\":1,\"attendCount\":1,"
                          + "\"lastAttendAt\":\"2023-11-30T11:10:40.9450682+09:00\"}],"
                          + "\"Money\":12000}")},
              schema = @Schema(
                  implementation = AttendInfoDto.Response.class))))
  @PostMapping()
  public AttendDto.Response attend() {
    return attendService.attend();
  }
}
