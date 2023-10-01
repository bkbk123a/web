package com.example.web.service.oauth;

import com.example.web.model.oauth.NaverToken;
import com.example.web.model.request.NaverOauthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class NaverOauthService {

    private static final String GRANT_TYPE = "authorization_code";
    private static final String authPath = "/oauth2.0/token";

    @Value("${oauth.naver.url.auth}")
    private String authUrl;

    @Value("${oauth.naver.url.api}")
    private String apiUrl;

    @Value("${oauth.naver.client-id}")
    private String clientId;

    @Value("${oauth.naver.secret}")
    private String clientSecret;

    /**
     * 외부 API(네이버) 인증
     *
     * @param request
     * @return 외부 API 응답의 접근 토큰
     */
    public String processOauth(NaverOauthRequest request) {
        String url = authUrl + authPath;

        HttpEntity<?> httpEntity = makeHttpEntity(request);
        RestTemplate restTemplate = new RestTemplate();
        NaverToken response = restTemplate.postForObject(url, httpEntity, NaverToken.class);

        checkResponse(response);

        return response.getAccessToken();
    }

    /**
     * 외부 API에 요청 보낼 HTTP 엔티티 생성 (HTTP 헤더 + HTTP 바디)
     *
     * @param request
     * @return 외부 API에 요청 보낼 HTTP 엔티티
     */
    private HttpEntity<?> makeHttpEntity(NaverOauthRequest request) {
        return new HttpEntity<>(makeHttpBody(request), makeHttpHeader());
    }

    private HttpHeaders makeHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return httpHeaders;
    }

    private HashMap<String, String> makeHttpBody(NaverOauthRequest request) {
        HashMap<String, String> httpBody = request.makeHttpBody();

        httpBody.put("grant_type", GRANT_TYPE);
        httpBody.put("client_id", clientId);
        httpBody.put("client_secret", clientSecret);

        return httpBody;
    }

    private void checkResponse(NaverToken response) {
        try {
            if (response == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("authUrl 로부터 응답을 받지 못하였습니다.");
        }
    }
}
