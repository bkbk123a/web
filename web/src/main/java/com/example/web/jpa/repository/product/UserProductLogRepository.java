package com.example.web.jpa.repository.product;

import com.example.web.jpa.entity.product.UserProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductLogRepository extends JpaRepository<UserProductLog, Long> {
}
