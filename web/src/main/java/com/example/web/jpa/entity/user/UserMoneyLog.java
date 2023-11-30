package com.example.web.jpa.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Money_Log",
    indexes = @Index(name = "Index_UserIndexCreatedAt",
        columnList = "UserIndex, CreatedAt"))
public class UserMoneyLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LogIndex", nullable = false)
  private Long logIndex;

  @Column(name = "UserIndex", nullable = false)
  private Long userIndex;

  @Column(name = "BeforeMoney", nullable = false)
  private Long beforeMoney;

  @Column(name = "AfterMoney", nullable = false)
  private Long afterMoney;

  @CreationTimestamp
  @Column(name = "CreatedAt", nullable = false)
  private OffsetDateTime createdAt;
}
