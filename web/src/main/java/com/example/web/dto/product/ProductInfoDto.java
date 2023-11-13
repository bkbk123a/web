package com.example.web.dto.product;

import com.example.web.jpa.entity.product.Product;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "상품 정보")
    @Builder.Default
    @JsonProperty("Products")
    private List<Product> products = new ArrayList<>();

  }
}