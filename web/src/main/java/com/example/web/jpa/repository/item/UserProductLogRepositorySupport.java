package com.example.web.jpa.repository.item;

import static com.example.web.jpa.entity.product.QUserProductLog.userProductLog;

import com.example.web.jpa.entity.product.UserProductLog;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserProductLogRepositorySupport {

  @Qualifier("QueryDslJpaQueryFactory")
  private final JPAQueryFactory queryFactory;

  /**
   * 유저 상품 로그 조회 유저 정보(userIndex)는 있어야 하며, 나머지 항목(productIndex, startTime, endTime)은 nullable. 조건에
   * 따라 동적 쿼리 생성
   *
   * @param userIndex    유저 인덱스
   * @param productIndex 싱품 인덱스
   * @param startTime    조회 시작 시간
   * @param endTime      조회 끝 시간
   * @return 유저 상품 로그
   */
  public List<UserProductLog> getUserProductLogs(long userIndex, Integer productIndex,
      LocalDateTime startTime, LocalDateTime endTime) {

    return queryFactory
        .selectFrom(userProductLog)
        .where(userProductLog.userIndex.eq(userIndex)
            .and(eqProductIndex(productIndex))
            .and(afterStartTime(startTime))
            .and(beforeEndTime(endTime)))
        .fetch();
  }

  /**
   * 상품 인덱스 조회 조건 설정
   *
   * @param productIndex 상품 인덱스
   * @return 상품 인덱스 있으면 상품 인덱스에 해당 하는 조회 조건 설정(없으면 전체 조회)
   */
  private BooleanExpression eqProductIndex(Integer productIndex) {

    return productIndex != null
        ? userProductLog.productIndex.eq(productIndex)
        : null;
  }

  /**
   * 조회 시작 시간 이상의 범위 설정
   *
   * @param startTime 조회 시작 시간
   * @return 시작 시간이 있으면 로그의 updatedAt이 시작 시간 이후로 조회 조건 동적 설정
   */
  private BooleanExpression afterStartTime(LocalDateTime startTime) {
    return startTime != null
        ? userProductLog.updatedAt.after(startTime.atOffset(ZoneOffset.UTC))
        : null;
  }

  /**
   * 조회 끝 시간 이하의 범위 설정
   *
   * @param endTime 조회 끝 시간
   * @return 끝 시간이 있으면 로그의 updatedAt이 끝 시간 이전으로 조회 조건 동적 설정
   */
  private BooleanExpression beforeEndTime(LocalDateTime endTime) {
    return endTime != null
        ? userProductLog.updatedAt.before(endTime.atOffset(ZoneOffset.UTC))
        : null;
  }
}
