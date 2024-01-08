package com.example.web.service.article;

import com.example.web.dto.article.UserArticleDetatilDto;
import com.example.web.dto.article.UserArticleDto;
import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.entity.article.UserArticleComment;
import com.example.web.jpa.repository.article.UserArticleRepository;
import com.example.web.jpa.repository.article.UserArticleRepositorySupport;
import com.example.web.model.enums.SearchType;
import com.example.web.model.exception.CustomErrorException;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

  private final UserArticleRepository userArticleRepository;
  private final UserArticleRepositorySupport userArticleRepositorySupport;

  public Page<UserArticle> getUserArticles(SearchType searchType, String searchKeyWord,
      Pageable pageable) {
    if (searchKeyWord == null || searchKeyWord.isBlank()) {
      return userArticleRepository.findAll(pageable);
    }

    return switch (searchType) {
      case TITLE -> userArticleRepository.findByTitleContaining(searchKeyWord, pageable);
      case CONTENT -> userArticleRepository.findByContentContaining(searchKeyWord, pageable);
      case ID -> userArticleRepository.findByUserInfo_UserIndexContaining(searchKeyWord, pageable);
      case NICKNAME ->
          userArticleRepository.findByUserInfo_NickNameContaining(searchKeyWord, pageable);
      case HASHTAG -> userArticleRepository.findByHashtag("#" + searchKeyWord, pageable);
    };
  }

  public UserArticleDetatilDto getUserArticleDetail(long articleId) {
    UserArticle userArticle = getUserArticleOrElseThrow(articleId);

    Set<UserArticleComment> userArticleComments = userArticle
        .getUserArticleComments();

    return UserArticleDetatilDto.builder()
        .userArticle(userArticle)
        .userArticleComments(userArticleComments)
        .build();
  }

  public UserArticle getUserArticleOrElseThrow(long articleId) {
    return userArticleRepository.findById(articleId)
        .orElseThrow(() -> CustomErrorException.builder().resultValue(1030).build());
  }

  public long getUserArticleCount() {
    return userArticleRepository.count();
  }

  public Page<UserArticle> getUserArticlesByHashtag(String hashtag, Pageable pageable) {
    if (hashtag == null || hashtag.isBlank()) {
      return Page.empty(pageable);
    }

    return userArticleRepository.findByHashtag(hashtag, pageable);
  }

  public List<String> getDistinctHashtags() {
    return userArticleRepositorySupport.getAllDistinctHashtags();
  }

  @Transactional
  public void saveArticle(UserArticleDto dto) {

    UserArticle userArticle = UserArticle.of(
        dto.userInfo(), dto.title(), dto.content(), dto.hashtag());

    userArticleRepository.save(userArticle);
  }

  @Transactional
  public void updateArticle(Long updateArticleIndex, UserArticleDto dto) {
    UserArticle userArticle = getUserArticleOrElseThrow(updateArticleIndex);

    if (!isArticleWriter(userArticle, dto)) {
      return;
    }

    userArticle.setTitle(dto.title());
    userArticle.setContent(dto.content());
    userArticle.setHashtag(dto.hashtag());

    userArticleRepository.save(userArticle);
  }

  /**
   * 게시글 변경(수정, 삭제)시 원작자 확인 판단
   *
   * @param changeUserArticle 변경할 게시글
   * @param dto               dto
   * @return 원작자 여부
   */
  private boolean isArticleWriter(UserArticle changeUserArticle, UserArticleDto dto) {
    return changeUserArticle.getUserInfo().getUserIndex()
        .equals(dto.userInfo().getUserIndex());
  }

  @Transactional
  public void deleteArticle(long articlendex, long userIndex) {
    // articleIndex 와 작성자의 userIndex 를 확인한다.
    userArticleRepository.deleteByArticleIndexAndUserInfo_UserIndex(articlendex, userIndex);
  }
}
