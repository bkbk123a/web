package com.example.web.jpa.repository.article;

import com.example.web.jpa.entity.article.UserArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArticleCommentRepository extends JpaRepository<UserArticleComment, Long> {

  void deleteByCommentIndexAndUserInfo_UserIndex(long commentIndex, long userIndex);
}
