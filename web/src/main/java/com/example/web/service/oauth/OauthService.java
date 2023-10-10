package com.example.web.service.oauth;

import com.example.web.jpa.entity.UserInfo;
import com.example.web.model.oauth.info.NaverUserInfo;
import com.example.web.model.request.NaverOauthRequest;
import com.example.web.model.response.OauthResponse;
import com.example.web.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final NaverOauthService naverOauthService;
    private final UserService userService;

    @Transactional
    public OauthResponse processNaverOauth(NaverOauthRequest request) {

        String accessToken = naverOauthService.processAccessToken(request);

        NaverUserInfo naverUserInfo = naverOauthService.processUserInfo(accessToken);

        Optional<UserInfo> userInfo = userService.getUserInfo(naverUserInfo.getEmailAddress());

        boolean isNewUSer = userInfo.isEmpty();

        if (!isNewUSer) {
            userService.login(isNewUSer, userInfo.get());
        }

        UserInfo newUserInfo = userService.saveUserInfo(naverUserInfo);

        return userService.login(isNewUSer, newUserInfo);
    }
}