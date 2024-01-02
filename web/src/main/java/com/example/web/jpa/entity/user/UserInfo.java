package com.example.web.jpa.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Info",
    indexes = {
        @Index(name = "Index_UserName", columnList = "UserName")})
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UserIndex", nullable = false)
  private Long userIndex;

  @Column(name = "EmailAddress", nullable = false, unique = true)
  private String emailAddress;

  @Column(name = "UserName", nullable = false, unique = true)
  private String userName;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "NickName", nullable = false)
  private String nickName;

  @Column(name = "CreatedAt", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "Money", nullable = false)
  @Builder.Default
  private Long money = 10000L; // 처음 기본으로 만원 제공한다.

  @Column(name = "LastLoginAt", nullable = false)
  @CreationTimestamp
  private LocalDateTime lastLoginAt;

  public void addMoney(Long addMoney) {
    this.money += addMoney;
  }

  public static UserInfo of(String userName, String password, String emailAddress, String nickName) {
    return UserInfo.builder()
        .userName(userName)
        .password(password)
        .emailAddress(emailAddress)
        .nickName(nickName)
        .build();
  }
}
