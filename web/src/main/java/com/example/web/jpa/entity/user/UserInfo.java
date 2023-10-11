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

    @Column(name = "LastLoginAt", nullable = false)
    @CreationTimestamp
    private LocalDateTime lastLoginAt;
}
