package com.example.web.jpa.entity.article;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//필드의 시간 관련값을 외부에서 받아오지 않는 이유
//중요도가 떨어지기 때문에 시스템 반영 시간으로 처리한다.

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ArticleBase {

  @Column(name = "CreatedAt", nullable = false, updatable = false)
  @CreationTimestamp
  private OffsetDateTime createdAt;   // 생성일시

  @Builder.Default
  @UpdateTimestamp
  @Column(name = "ModifiedAt", nullable = false)
  private OffsetDateTime modifiedAt = OffsetDateTime.now();  // 수정일시

  @Builder.Default
  @Column(name = "ModifyUserIndex", nullable = false, length = 100)
  private Long modiftUserIndex = 0L;       // 수정한 사람
}
