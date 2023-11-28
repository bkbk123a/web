package com.example.web.dto.product;

import com.example.web.jpa.entity.product.Product;
import com.example.web.model.enums.ProductType;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class ProductEditDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  public static class Request {

    @Schema(example = "1", description = "상품 타입")
    @JsonProperty("ProductType")
    private ProductType productType;

    @Schema(example = "상의1", description = "상품 이름")
    @NotBlank
    @JsonProperty("ProductName")
    private String productName;

    @Schema(example = "100", description = "상품 가격")
    @Min(0)
    @JsonProperty("Price")
    private Integer price;

    @Schema(example = "9999", description = "상품 수량")
    @Min(0)
    @JsonProperty("Quantity")
    private Integer quantity;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "수정된 상품 정보")
    @JsonProperty("Product")
    private Product product;
  }
}