package com.example.web.service;

import com.example.web.model.request.OauthCommonRequest;
import com.example.web.model.response.OauthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    public OauthResponse oauth(OauthCommonRequest request) {

        return OauthResponse.builder().build();
    }
}
