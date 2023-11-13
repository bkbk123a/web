package com.example.web.controller;

import com.example.web.dto.product.ProductBuyDto;
import com.example.web.dto.product.ProductInfoDto;
import com.example.web.dto.product.UserProductInfoDto;
import com.example.web.model.annotation.IgnoreAuth;
import com.example.web.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @IgnoreAuth
  @Operation(
      summary = "상품 정보 조회",
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
    return productService.getProductssInfo();
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
  @GetMapping("/user-info")
  public UserProductInfoDto.Response getUserProductInfo() {
    return productService.getUserProductsInfo();
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
                      value = "{\"ResultVal\":0,\"ResultMsg\":\"Success\",\"UserMoney\":9900," +
                          "\"UserProduct\":{\"userIndex\":1,\"productIndex\":1,\"productCount\":1," +
                          "\"updatedAt\":\"2023-11-13T18:03:56.747447+09:00\"}}")},
              schema = @Schema(
                  implementation = ProductBuyDto.Response.class))))
  @PostMapping("buy")
  @ResponseBody
  public ProductBuyDto.Response buyProduct(@Valid @RequestBody ProductBuyDto.Request request) {
    return productService.buyProduct(request);
  }
}
