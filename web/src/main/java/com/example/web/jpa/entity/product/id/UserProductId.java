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

  private Integer productIndex;
}
