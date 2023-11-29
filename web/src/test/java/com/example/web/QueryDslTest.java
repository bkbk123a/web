package com.example.web;

import com.example.web.jpa.entity.product.UserProductLog;
import com.example.web.jpa.repository.item.UserProductLogRepositorySupport;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryDslTest {

  @Autowired
  private UserProductLogRepositorySupport userProductLogRepositorySupport;

  @Test
  public void basicTest() {

    int userIndex = 1;
    Integer productIndex = 1;
    LocalDateTime startTime = LocalDateTime.of(2023, 11, 28, 00, 00, 00);
    LocalDateTime endTime = LocalDateTime.of(2023, 11, 29, 00, 00, 00);

    List<UserProductLog> logs = userProductLogRepositorySupport
        .getUserProductLogs(userIndex, productIndex, startTime, endTime);

    int iTemp = 0;

    return;
  }
}
