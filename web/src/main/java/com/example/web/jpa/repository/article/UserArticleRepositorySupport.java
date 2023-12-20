package com.example.web.jpa.repository.article;

import com.example.web.jpa.entity.article.QUserArticle;
import com.example.web.jpa.entity.article.UserArticle;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserArticleRepositorySupport extends QuerydslRepositorySupport {

  public UserArticleRepositorySupport() {
    super(UserArticle.class);
  }

  /**
   * 해시 태그 이름 조회
   * 조회 조건 : 해시 태그 not null && 중복 허용 X
   *
   * @return 해시 태그 이름들
   */
  public List<String> getAllDistinctHashtags() {
    QUserArticle userArticle = QUserArticle.userArticle;

    return from(userArticle)
        .distinct()
        .select(userArticle.hashtag)
        .where(userArticle.hashtag.isNotNull())
        .fetch();
  }
}
