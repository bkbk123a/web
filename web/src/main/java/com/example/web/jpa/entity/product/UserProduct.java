package com.example.web.jpa.entity.product;

import com.example.web.jpa.entity.product.id.UserProductId;
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
@Table(name = "T_User_Product")
@IdClass(UserProductId.class)
public class UserProduct {

  @Id
  @Column(name = "UserIndex")
  private Long userIndex;

  @Id
  @Column(name = "ProductIndex")
  private Integer productIndex;

  @Column(name = "ProductCount", nullable = false)
  @Builder.Default
  private Integer productCount = 0;

  @UpdateTimestamp
  @Column(name = "UpdatedAt", nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  public void addProductCount(int productCount) {
    this.productCount += productCount;
  }
}
