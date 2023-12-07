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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ProductIndex")
  private Product product;

  @Column(name = "ProductCount", nullable = false)
  @Builder.Default
  private Integer productCount = 0;

  @UpdateTimestamp
  @Column(name = "UpdatedAt", nullable = false)
  @Builder.Default
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  public void addProductCount(int productCount) {
    this.productCount += productCount;
  }

//  연관 관계 편의 메서드
//  public void setProduct(Product product) {
//    this.product = product;
//    기획 데이터는 유저 정보 관리하지 않는다.
//    //product.getUserProducts().add(this);
//  }
}
