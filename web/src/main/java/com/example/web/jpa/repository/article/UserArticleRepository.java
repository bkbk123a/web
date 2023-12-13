package com.example.web.jpa.repository.article;

import com.example.web.jpa.entity.article.UserArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleRepository extends JpaRepository<UserArticle, Long> {
  Page<UserArticle> findByTitleContaining(String title, Pageable pageable);
  Page<UserArticle> findByContentContaining(String content, Pageable pageable);
  Page<UserArticle> findByUserInfo_UserIndexContaining(String userIndex, Pageable pageable);
  Page<UserArticle> findByUserInfo_NickNameContaining(String nickName, Pageable pageable);
  Page<UserArticle> findByHashtag(String hashtag, Pageable pageable);

}
