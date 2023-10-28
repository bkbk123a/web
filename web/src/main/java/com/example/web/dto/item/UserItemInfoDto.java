package com.example.web.dto.item;

import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.item.UserItem;
import com.example.web.model.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserItemInfoDto {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Setter
    @SuperBuilder
    @EqualsAndHashCode(callSuper = true)
    public static class Response extends CommonResponse {

        @Builder.Default
        @JsonProperty("UserItems")
        private List<UserItem> userItems = new ArrayList<>();

    }
}
