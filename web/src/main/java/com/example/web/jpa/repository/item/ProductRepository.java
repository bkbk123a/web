package com.example.web.jpa.repository.item;

import com.example.web.jpa.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
