package com.example.web.dto.item;

import com.example.web.jpa.entity.item.Item;
import com.example.web.jpa.entity.item.UserItem;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class ItemBuyDto {

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  public static class Dto {
    private UserInfo userInfo;
    private Item item;
    private UserItem userItem;
    private Request request;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  public static class Request {

    @JsonProperty("ItemIndex")
    private int itemIndex;

    @JsonProperty("ItemCount")
    private int itemCount;
  }

  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @Getter
  @Setter
  @SuperBuilder
  @EqualsAndHashCode(callSuper = true)
  public static class Response extends CommonResponse {

    @JsonProperty("UserMoney")
    private long userMoney;

    @JsonProperty("AddUserItem")
    private UserItem userItem;  // 구매 후 유저 아이템 정보
  }
}
