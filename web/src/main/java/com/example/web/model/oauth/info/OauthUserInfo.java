package com.example.web.model.oauth.info;

import com.example.web.model.enums.OauthType;

// 현재는 NaveOauth 만 되어있는데, 추후 확장 가능하다.
public interface OauthUserInfo {

    OauthType getOauthType();

    String getEmailAddress();

    String getNickName();
}
