package com.example.web.jpa.repository.item;

import com.example.web.jpa.entity.product.QUserProductLog;
import com.example.web.jpa.entity.product.UserProductLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserProductLogRepositorySupport {
  @Qualifier("QueryDslJpaQueryFactory")
  private final JPAQueryFactory queryFactory;

  public List<UserProductLog> getUserProductLogs(int productIndex, long userIndex) {

    QUserProductLog log = new QUserProductLog("log");

    return queryFactory
        .selectFrom(log)
        .where(log.userIndex.eq(userIndex)
            .and(log.productIndex.eq(productIndex)))
        .fetch();
  }
}
