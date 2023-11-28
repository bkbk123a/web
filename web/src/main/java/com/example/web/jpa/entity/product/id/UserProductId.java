package com.example.web.jpa.entity.product.id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@EqualsAndHashCode
public class UserProductId implements Serializable {

  private Long userIndex;
  // T_STATIC_Product 엔티티의 productIndex 이다.
  private Integer product;
}
