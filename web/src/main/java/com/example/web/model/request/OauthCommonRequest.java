package com.example.web.model.request;

import com.example.web.model.enums.OauthType;

import java.util.HashMap;

public interface OauthCommonRequest {

    OauthType getOauthType();
    HashMap<String, String> makeHttpBody();
}
