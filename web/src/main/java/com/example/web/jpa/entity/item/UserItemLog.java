package com.example.web.jpa.entity.item;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Item_Log")
public class UserItemLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LogIndex")
  private Long logIndex;

  @Column(name = "UserIndex")
  private Long userIndex;

  @Column(name = "ItemIndex")
  private Integer itemIndex;

  @Column(name = "BeforeItemCount", nullable = false)
  @Builder.Default
  private Integer beforeItemCount = 0;

  @Column(name = "AfterItemCount", nullable = false)
  @Builder.Default
  private Integer afterItemCount = 0;

  @Column(name = "UpdatedAt", nullable = false)
  @Builder.Default
  private OffsetDateTime updatedAt = OffsetDateTime.now();
}
