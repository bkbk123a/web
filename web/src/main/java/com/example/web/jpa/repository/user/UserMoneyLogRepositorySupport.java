package com.example.web.jpa.repository.user;

import com.example.web.jpa.entity.user.UserMoneyLog;
import com.example.web.model.enums.MoneyLogType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

import static com.example.web.jpa.entity.user.QUserMoneyLog.userMoneyLog;

@RequiredArgsConstructor
@Repository
public class UserMoneyLogRepositorySupport {

  @Qualifier("QueryDslJpaQueryFactory")
  private final JPAQueryFactory queryFactory;

  /**
   * 유저 돈 변화 로그
   * 유저 정보(userIndex)는 있어야 하며, 나머지 항목 들은 nullable.
   * 조건에 따라 동적 쿼리 생성
   *
   * @param userIndex 유저 인덱스
   * @param logType   로그 타입
   * @param startTime 조회 시작 시간
   * @param endTime   조회 끝 시간
   * @return          유저 돈 변화 로그
   */
  public List<UserMoneyLog> getUserMoneyLogs(long userIndex, Integer logType,
      LocalDateTime startTime, LocalDateTime endTime) {

    return queryFactory
        .selectFrom(userMoneyLog)
        .where(userMoneyLog.userIndex.eq(userIndex)
            .and(eqMoneyLogType(logType))
            .and(afterStartTime(startTime))
            .and(beforeEndTime(endTime)))
        .fetch();
  }

  /**
   * 조회 로그 타입 설정
   *
   * @param moneyLogType 로그 타입
   * @return 로그 타입이 있으면 로그 타입에 해당 하는 조회 조건 동적 설정
   */
  private BooleanExpression eqMoneyLogType(Integer moneyLogType) {

    return moneyLogType != null
        ? userMoneyLog.logType.eq(MoneyLogType.ofMoneyLogType(moneyLogType))
        : null;
  }

  /**
   * 조회 최소 시간 이상의 범위 설정
   *
   * @param startTime 조회 최소 시간
   * @return 최소 시간이 있으면 로그의 createdAt이 최소 시간 이후로 조회 조건 동적 설정
   */
  private BooleanExpression afterStartTime(LocalDateTime startTime) {
    return startTime != null
        ? userMoneyLog.createdAt.after(convert(startTime))
        : null;
  }

  /**
   * 조회 최대 시간 이하의 범위 설정
   *
   * @param endTime 조회 최대 시간
   * @return 최대 시간이 있으면 로그의 createdAt이 최대 시간 이전으로 조회 조건 동적 설정
   */
  private BooleanExpression beforeEndTime(LocalDateTime endTime) {
    return endTime != null
        ? userMoneyLog.createdAt.before(convert(endTime))
        : null;
  }

  private OffsetDateTime convert(LocalDateTime time) {
    return time.atZone(ZoneId.systemDefault()).toOffsetDateTime();
  }
}
