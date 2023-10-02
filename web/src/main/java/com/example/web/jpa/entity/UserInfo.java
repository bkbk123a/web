package com.example.web.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
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

    @Column(name = "LastLoginAt", nullable = false)
    @Builder.Default
    private LocalDateTime lastLoginAt = LocalDateTime.now();
}
