package com.example.web.jpa.repository.product;

import com.example.web.jpa.entity.product.UserProduct;
import com.example.web.jpa.entity.product.id.UserProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, UserProductId> {

  List<UserProduct> findByUserIndex(Long userIndex);
}
