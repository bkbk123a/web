package com.example.web.jpa.entity.item;

import com.example.web.jpa.entity.item.id.UserItemId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Item")
@IdClass(UserItemId.class)
public class UserItem {

  @Id
  @Column(name = "UserIndex")
  private Long userIndex;

  @Id
  @Column(name = "ItemIndex")
  private Integer itemIndex;

  @Column(name = "ItemCount", nullable = false)
  @Builder.Default
  private Integer itemCount = 0;

  @UpdateTimestamp
  @Column(name = "UpdatedAt", nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  public void addItemCount(int itemCount) {
    this.itemCount += itemCount;
  }
}
