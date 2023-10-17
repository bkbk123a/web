package com.example.web.model.oauth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class JwtUser {

    private long userIndex;

    // 필요시 추후 정보 추가.
}
