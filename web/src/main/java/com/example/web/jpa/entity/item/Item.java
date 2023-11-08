package com.example.web.jpa.entity.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_STATIC_Item")
public class Item {
  // 기획 아이템 테이블
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ItemIndex")
  private Integer itemIndex;

  @Column(name = "ItemName", nullable = false)
  @Nationalized
  private String itemName;

  @Column(name = "Price", nullable = false)
  private Integer price;

  @Column(name = "Quantity", nullable = false)
  private Integer quantity;

  public void addItemQuantity(int quantity) {
    this.quantity += quantity;
  }
}
