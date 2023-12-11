package com.example.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.article.UserArticleCommentRepository;
import com.example.web.jpa.repository.article.UserArticleRepository;
import com.example.web.jpa.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@DisplayName("Article 관련 테스트")
public class ArticleRepositoryTest {

  private final UserArticleRepository userArticleRepository;

  private final UserArticleCommentRepository userArticleCommentRepository;
  private final UserRepository userRepository;

  public ArticleRepositoryTest(
      @Autowired UserArticleRepository userArticleRepository,
      @Autowired UserArticleCommentRepository userArticleCommentRepository,
      @Autowired UserRepository userRepository) {
    this.userArticleRepository = userArticleRepository;
    this.userArticleCommentRepository = userArticleCommentRepository;
    this.userRepository = userRepository;
  }

  @BeforeEach
  void createUserInfo () {
    UserInfo userInfo = UserInfo.builder()
        .emailAddress("beomkyung@naver.com")
        .nickName("beomkyung")
        .build();

    userRepository.save(userInfo);
  }

  UserArticle createUserArticle(UserInfo userInfo) {
    return UserArticle
        .of(userInfo, "Title", "Content", "#Spring", userInfo.getNickName());
  }

  @DisplayName("insert 테스트")
  @Test
  void givenUserArticle_whenInserting_thenCountIncrease() {
    // Given
    long previousCount = userArticleRepository.count();

    UserInfo userInfo = userRepository.getById(1L);
    UserArticle userArticle = createUserArticle(userInfo);

    // When
    userArticleRepository.save(userArticle);

    // Then
    assertThat(userArticleRepository.count()).isEqualTo(previousCount + 1);
  }

  @DisplayName("update 테스트")
  @Test
  void giveUserArticle_whenUpdating_thenWorksFine() throws InterruptedException {
    // Given
    UserInfo userInfo = userRepository.getById(1L);
    UserArticle userArticle = createUserArticle(userInfo);
    userArticleRepository.save(userArticle);

    // When
    final String content = "new content";
    userArticle.modifyContent(userInfo.getNickName(), content);
    UserArticle savedArticle = userArticleRepository.save(userArticle);

    // Then
    assertThat(savedArticle).hasFieldOrPropertyWithValue("content", content);
  }
}
