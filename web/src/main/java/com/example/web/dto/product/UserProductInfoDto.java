package com.example.web.dto.product;

import com.example.web.jpa.entity.product.UserProduct;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserProductInfoDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @Builder.Default
    @JsonProperty("UserProducts")
    private List<UserProduct> userProducts = new ArrayList<>();

  }
}
