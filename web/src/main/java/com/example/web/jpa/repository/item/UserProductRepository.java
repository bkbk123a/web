package com.example.web.jpa.repository.item;

import com.example.web.jpa.entity.product.UserProduct;
import com.example.web.jpa.entity.product.id.UserProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct, UserProductId> {

  List<UserProduct> findByUserIndex(Long userIndex);
}
