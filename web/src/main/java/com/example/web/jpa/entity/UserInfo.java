package com.example.web.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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

    @Column(name = "CreatedTime", nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(name = "LastLoginTime", nullable = false)
    @CreationTimestamp
    private OffsetDateTime lastLoginTime;
}
