package com.example.web.jpa.repository.user;

import com.example.web.jpa.entity.user.UserMoneyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoneyLogRepository extends JpaRepository<UserMoneyLog, Long> {

}
