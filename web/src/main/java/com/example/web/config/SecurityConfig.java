package com.example.web.config;

import com.example.web.dto.security.CustomUserDetails;
import com.example.web.jpa.repository.user.UserRepository;
import com.example.web.model.exception.CustomErrorException;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
        .formLogin()
        .and()
        .logout()
          .logoutSuccessUrl("/")
        .and()
        .build();
  }

  // Spring Security에서 유저의 정보를 가져오는 인터페이스
  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> userRepository
        .findByUserName(username)
        .map(CustomUserDetails::from)
        .orElseThrow(() -> CustomErrorException.builder().resultValue(2).build());
  }

  // 비밀번호 인코딩
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
