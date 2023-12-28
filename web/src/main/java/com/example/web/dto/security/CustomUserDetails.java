package com.example.web.dto.security;

import com.example.web.jpa.entity.user.UserInfo;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record CustomUserDetails(
    String userName,
    String password,
    Collection<? extends GrantedAuthority> authorities,
    String email,
    String nickname
) implements UserDetails {
  // Spring Security에서 사용자의 정보를 담는 인터페이스
  public static CustomUserDetails of(String userName, String password, String email, String nickName) {
    Set<RoleType> roleTypes = Set.of(RoleType.USER);

    return new CustomUserDetails(
        userName,
        password,
        roleTypes.stream()
            .map(RoleType::getName)
            .map(SimpleGrantedAuthority::new) // GrantedAuthority 구현체
            .collect(Collectors.toUnmodifiableSet())
        ,
        email,
        nickName
    );
  }

  public static CustomUserDetails from(UserInfo userInfo) {
    return CustomUserDetails.of(userInfo.getUserName(),
        userInfo.getPassword(),
        userInfo.getEmailAddress(),
        userInfo.getEmailAddress());
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
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
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
