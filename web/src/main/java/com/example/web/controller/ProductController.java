package com.example.web.controller;

import com.example.web.dto.product.UserProductBuyDto;
import com.example.web.dto.product.ProductEditDto;
import com.example.web.dto.product.ProductInfoDto;
import com.example.web.dto.product.UserProductLogDto;
import com.example.web.dto.product.UserProductInfoDto;
import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "product", description = "상품 관련")
@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @IgnoreAuth
  @Operation(
      summary = "상품 전체 정보 조회",
      description = "JWT 필요 없음",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\"," +
                          "\"Products\":[{\"productIndex\":1,\"productType\":2," +
                          "\"productName\":\"청바지1\",\"price\":100,\"quantity\":999}," +
                          "{\"productIndex\":5,\"productType\":1," +
                          "\"productName\":\"상의1\",\"price\":100,\"quantity\":999}," +
                          "{\"productIndex\":9,\"productType\":1," +
                          "\"productName\":\"신발1\",\"price\":100,\"quantity\":999}]}")},
              schema = @Schema(
                  implementation = ProductInfoDto.Response.class))))
  @GetMapping("/info")
  public ProductInfoDto.Response getProductInfo() {
    return productService.getProductInfo();
  }

  @IgnoreAuth
  @Operation(
      summary = "상품 수정",
      description = "JWT 필요 없음",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\"," +
                          "\"Product\":{\"productIndex\":14,\"productType\":1," +
                          "\"productName\":\"stringTTTT\",\"price\":7,\"quantity\":5}}")},
              schema = @Schema(
                  implementation = ProductEditDto.Response.class))))
  @PostMapping("edit")
  @ResponseBody
  public ProductEditDto.Response editProduct(
      @Valid @RequestBody ProductEditDto.Request request) {
    return productService.editProduct(request);
  }

  @Operation(
      summary = "유저의 상품 정보 조회",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\",\"UserProducts\":[]}")},
              schema = @Schema(
                  implementation = UserProductInfoDto.Response.class))))
  @GetMapping("user-info")
  public UserProductInfoDto.Response getUserProductInfo() {
    return productService.getUserProductInfo();
  }

  @Operation(
      summary = "유저의 상품 구매",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\","
                          + "\"UserMoney\":9900,\"UserProduct\":{\"userIndex\":1,"
                          + "\"productIndex\":1,\"productCount\":1," +
                          "\"updatedAt\":\"2023-11-13T18:03:56.747447+09:00\"}}")},
              schema = @Schema(
                  implementation = UserProductBuyDto.Response.class))))
  @PostMapping("buy")
  @ResponseBody
  public UserProductBuyDto.Response buyUserProduct(
      @Valid @RequestBody UserProductBuyDto.Request request) {
    return productService.buyUserProduct(request);
  }

  @Operation(
      summary = "유저의 상품 관련 로그 조회",
      description = "JWT 헤더에 추가 필요(로그인 하세요)",
      responses = @ApiResponse(
          description = "OK",
          responseCode = "200",
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\","
                          + "\"UserProductLogs\":[{\"logIndex\":1,\"userIndex\":1,"
                          + "\"productIndex\":1,\"beforeProductCount\":2,\"afterProductCount\":1,"
                          + "\"updatedAt\":\"2023-11-26T12:39:23.585Z\"}]}")},
              schema = @Schema(
                  implementation = UserProductLogDto.Response.class))))
  @GetMapping("log-info")
  @ResponseBody
  public UserProductLogDto.Response getUserProductLog(
      @Parameter(example = "1", description = "상품 인덱스")
      @RequestParam(name = "ProductIndex", required = false)
      Integer productIndex,
      @Parameter(example = "2023-01-01T00:00:00", description = "조회 시작 시간")
      @RequestParam(name = "StartTime", required = false)
      LocalDateTime startTime,
      @Parameter(example = "2023-12-31T00:00:00", description = "조회 끝 시간")
      @RequestParam(name = "EndTime", required = false)
      LocalDateTime endTime) {

    return productService.getUserProductLog(productIndex, startTime, endTime);
  }
}
