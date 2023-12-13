package com.example.web.service.article;

import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.repository.article.UserArticleRepository;
import com.example.web.model.enums.SearchType;
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

  public Page<UserArticle> getUserArticles(SearchType searchType, String searchKeyWord, Pageable pageable) {
    if (searchKeyWord == null || searchKeyWord.isBlank()) {
      return userArticleRepository.findAll(pageable);
    }

    return switch (searchType) {
      case TITLE -> userArticleRepository.findByTitleContaining(searchKeyWord, pageable);
      case CONTENT -> userArticleRepository.findByContentContaining(searchKeyWord, pageable);
      case ID -> userArticleRepository.findByUserInfo_UserIndexContaining(searchKeyWord, pageable);
      case NICKNAME -> userArticleRepository.findByUserInfo_NickNameContaining(searchKeyWord, pageable);
      case HASHTAG -> userArticleRepository.findByHashtag("#" + searchKeyWord, pageable);
    };
  }
}
