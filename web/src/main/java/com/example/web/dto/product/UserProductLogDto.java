package com.example.web.dto.product;

import com.example.web.jpa.entity.product.UserProductLog;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class UserProductLogDto {

  @Getter
  @SuperBuilder
  public static class Dto {
    private long userIndex;
    private Integer productIndex;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {
    @Schema(description = "유저의 상품 구매 로그")
    @Builder.Default
    @JsonProperty("UserProductLogs")
    private List<UserProductLog> userProductLogs = new ArrayList<>();
  }
}
