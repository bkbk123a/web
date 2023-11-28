package com.example.web;

import com.example.web.jpa.repository.item.UserProductLogRepositorySupport;
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
    int productIndex = 1;

    var rst = userProductLogRepositorySupport.getUserProductLogs(userIndex, productIndex);

    int iTemp = 0;
    return;
  }
}
