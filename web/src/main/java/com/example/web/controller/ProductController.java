package com.example.web.controller;

import com.example.web.dto.product.ProductBuyDto;
import com.example.web.dto.product.ProductInfoDto;
import com.example.web.dto.product.UserProductInfoDto;
import com.example.web.service.product.ProductService;
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

  @GetMapping("/info")
  public ProductInfoDto.Response getProductInfo() {
    return productService.getProductssInfo();
  }

  @GetMapping("/user-info")
  public UserProductInfoDto.Response getUserProductInfo() {
    return productService.getUserProductsInfo();
  }

  @PostMapping("buy")
  @ResponseBody
  public ProductBuyDto.Response buyProduct(@Valid @RequestBody ProductBuyDto.Request request) {
    return productService.buyProduct(request);
  }
}
