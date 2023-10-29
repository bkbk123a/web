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
@Table(name = "T_User_Info")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UserIndex", nullable = false)
  private Long userIndex;

  @Column(name = "EmailAddress", nullable = false, unique = true)
  private String emailAddress;

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

  public void plusMoney(Long addMoney) {
    this.money += addMoney;
  }
}
