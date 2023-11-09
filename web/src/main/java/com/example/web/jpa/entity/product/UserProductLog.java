package com.example.web.jpa.entity.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Product_Log")
public class UserProductLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LogIndex")
  private Long logIndex;

  @Column(name = "UserIndex")
  private Long userIndex;

  @Column(name = "ItemIndex")
  private Integer itemIndex;

  @Column(name = "BeforeProductCount", nullable = false)
  @Builder.Default
  private Integer beforeProductCount = 0;

  @Column(name = "AfterProductCount", nullable = false)
  @Builder.Default
  private Integer afterProductCount = 0;

  @Column(name = "UpdatedAt", nullable = false)
  @Builder.Default
  private OffsetDateTime updatedAt = OffsetDateTime.now();
}
