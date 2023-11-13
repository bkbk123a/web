package com.example.web.controller;

import com.example.web.model.annotation.IgnoreAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @IgnoreAuth
  @Operation(
      summary = "테스트",
      description = "JWT 필요 없음",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"result\":0,\"Msg\":\"Success\"}")},
              schema = @Schema(
                  implementation = ResBody.class))))
  @PostMapping("/test")
  public ResBody test(@RequestBody ReqBody reqBody) {

    return new ResBody("test");
  }

  @Data
  private static class ReqBody {
    private String foo;
    private String bar;
  }

  @AllArgsConstructor
  @Data
  private static class ResBody {
    private String result;
  }
}
