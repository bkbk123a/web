package com.example.web.jpa.entity.product;

import com.example.web.model.enums.ProductType;
import com.example.web.model.enums.converter.ProductTypeConverter;
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
@Table(name = "T_STATIC_Product")
public class Product {
  // 기획 상품 테이블
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ProductIndex")
  private Integer productIndex;

  @Column(name = "ProductType", nullable = false)
  @Convert(converter = ProductTypeConverter.class)
  private ProductType productType;

  @Column(name = "ProductName", nullable = false, unique = true)
  @Nationalized
  private String productName;

  @Column(name = "Price", nullable = false)
  private Integer price;

  @Column(name = "Quantity", nullable = false)
  private Integer quantity;

  public void addProductQuantity(int quantity) {
    this.quantity += quantity;
  }
}
