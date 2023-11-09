package com.example.web.dto.product;

import com.example.web.jpa.entity.product.Product;
import com.example.web.jpa.entity.product.UserProduct;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  public static class Request {

    @JsonProperty("ProductIndex")
    private int productIndex;

    @JsonProperty("ProductCount")
    private int productCount;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @JsonProperty("UserMoney")
    private long userMoney;

    @JsonProperty("UserProduct")
    private UserProduct userProduct;  // 구매 후 유저 아이템 정보
  }
}
