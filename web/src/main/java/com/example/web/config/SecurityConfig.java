package com.example.web.config;

import com.example.web.dto.security.CustomUserDetails;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.model.exception.CustomErrorException;
import com.example.web.model.response.KakaoOAuth2Response;
import com.example.web.service.user.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService)
      throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers(
                HttpMethod.GET,
                "/",
                "/articles",
                "/articles/search-hashtag"
            ).permitAll() // 정적컨텐츠와 Get요청과 위의 내용 제외한 내용들 요청시 인증 필요
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .logout(logout -> logout.logoutSuccessUrl("/"))
        .oauth2Login(oauth -> oauth
            .userInfoEndpoint(userInfo -> userInfo
                .userService(oAuth2UserService)))
        .build();
  }

  // Spring Security에서 자동으로 유저의 정보를 가져오는 인터페이스
  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return username -> userService
        .getUserInfoByUserName(username)
        .map(CustomUserDetails::from)
        .orElseThrow(() -> CustomErrorException.builder().resultValue(1000).build());
  }

  @Bean
  public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
      UserService userService,
      PasswordEncoder passwordEncoder
  ) {
    final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    return userRequest -> {
      // application.yml 을 토대로 요청을 보내고, 카카오톡에서 유저의 정보를 얻어온다.
      OAuth2User oAuth2User = delegate.loadUser(userRequest);

      KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());

      String registrationId = userRequest.getClientRegistration().getRegistrationId();
      String kakaoResponseUserId = String.valueOf(kakaoResponse.id());
      String username = registrationId + "_" + kakaoResponseUserId;
      String dummyPassword = passwordEncoder.encode("{bcrypt}" + UUID.randomUUID());

      // userName 으로 db 에서 조회 후, 없으면 회원 정보 저장한다.
      return userService.getUserInfoByUserName(username)
          .map(CustomUserDetails::from)
          .orElseGet(() ->
              CustomUserDetails.from(
                  userService.saveUserInfo(
                      UserInfo.of(username, dummyPassword, kakaoResponse.email(),
                          kakaoResponse.nickname()))
              ));
    };
  }

  // 비밀번호 인코딩
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
