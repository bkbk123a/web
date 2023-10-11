package com.example.web.model.request.oauth;

import com.example.web.model.enums.OauthType;
import org.springframework.util.MultiValueMap;

public interface OauthCommonRequest {

    OauthType getOauthType();
    MultiValueMap<String, String> makeHttpBody();
}
