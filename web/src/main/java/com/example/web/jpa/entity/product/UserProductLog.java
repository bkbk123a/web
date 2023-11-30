package com.example.web.jpa.entity.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Product_Log",
    indexes = @Index(name = "Index_UserIndexProductIndexCreatedAt",
        columnList = "UserIndex, ProductIndex, CreatedAt"))
public class UserProductLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LogIndex")
  private Long logIndex;

  @Column(name = "UserIndex", nullable = false)
  private Long userIndex;

  @Column(name = "ProductIndex", nullable = false)
  private Integer productIndex;

  @Column(name = "BeforeProductCount", nullable = false)
  private Integer beforeProductCount;

  @Column(name = "AfterProductCount", nullable = false)
  private Integer afterProductCount;

  @Column(name = "CreatedAt", nullable = false)
  @CreationTimestamp
  private OffsetDateTime createdAt;
}
