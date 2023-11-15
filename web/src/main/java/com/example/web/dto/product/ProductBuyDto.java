package com.example.web.dto.product;

import com.example.web.jpa.entity.product.Product;
import com.example.web.jpa.entity.product.UserProduct;
import com.example.web.jpa.entity.product.UserProductLog;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class ProductBuyDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  public static class Dto {
    private UserInfo userInfo;
    private Product product;
    private UserProduct userProduct;
    private Request request;
    private UserProductLog userProductLog;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  public static class Request {

    @Schema(description = "상품 인덱스")
    @Min(0)
    @JsonProperty("ProductIndex")
    private int productIndex;

    @Schema(description = "상품 수량")
    @Min(0)
    @JsonProperty("ProductCount")
    private int productCount;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Schema(description = "유저의 돈")
    @JsonProperty("UserMoney")
    private long userMoney;

    @Schema(description = "구매 후 유저의 해당 아이템 정보")
    @JsonProperty("UserProduct")
    private UserProduct userProduct;
  }
}
