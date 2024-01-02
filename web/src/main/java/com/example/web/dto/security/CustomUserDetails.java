package com.example.web.dto.security;

import com.example.web.jpa.entity.user.UserInfo;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

// Spring security 에서 사용자 정보를 담는 클래스
public record CustomUserDetails(
    long userIndex,
    String userName,
    String password,
    Collection<? extends GrantedAuthority> authorities,
    String email,
    String nickName,
    Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

  public static CustomUserDetails of(long userIndex, String userName, String password, String email,
      String nickName) {

    return of(userIndex, userName, password, email, nickName, Map.of());
  }

  public static CustomUserDetails of(long userIndex, String userName, String password, String email,
      String nickName, Map<String, Object> oAuth2Attributes) {
    Set<RoleType> roleTypes = Set.of(RoleType.USER);

    return new CustomUserDetails(
        userIndex,
        userName,
        password,
        roleTypes.stream()
            .map(RoleType::getName)
            .map(SimpleGrantedAuthority::new) // GrantedAuthority 구현체
            .collect(Collectors.toUnmodifiableSet())
        ,
        email,
        nickName,
        oAuth2Attributes
    );
  }

  public static CustomUserDetails from(UserInfo userInfo) {
    return CustomUserDetails.of(
        userInfo.getUserIndex(),
        userInfo.getUserName(),
        userInfo.getPassword(),
        userInfo.getEmailAddress(),
        userInfo.getNickName());
  }

  public UserInfo toUserInfo() {
    return UserInfo.builder()
        .userIndex(userIndex)
        .emailAddress(email)
        .userName(userName)
        .password(password)
        .nickName(nickName)
        .build();
  }

  // 계정의 권한 목록을 리턴
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return oAuth2Attributes;
  }

  @Override
  public String getName() {
    return userName;
  }

  public enum RoleType {
    USER("ROLE_USER");

    @Getter
    private final String name;

    RoleType(String name) {
      this.name = name;
    }
  }
}
