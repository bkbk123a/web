package com.example.web.service.article;

import com.example.web.dto.article.UserArticleCommentDto;
import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.entity.article.UserArticleComment;
import com.example.web.jpa.repository.article.UserArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

  private final ArticleService articleService;
  private final UserArticleCommentRepository userArticleCommentRepository;

  public void saveArticleComment(UserArticleCommentDto dto) {
    UserArticle userArticle = articleService.getUserArticleOrElseThrow(dto.articleIndex());

    UserArticleComment userArticleComment = UserArticleComment.of(
        dto.userInfo().getUserIndex(), dto.content(), dto.userInfo(), userArticle);

    userArticleCommentRepository.save(userArticleComment);
  }

  public void deleteArticleComment(long commentIndex, long userIndex) {
    userArticleCommentRepository.deleteByCommentIndexAndUserInfo_UserIndex(commentIndex, userIndex);
  }

  //  /**
  //   * 유저 게시글 댓글 리스트 조회 유저 게시글 댓글 엔티티의 외래키를(articleIndex) 통해 조회
  //   *
  //   * @param articleIndex
  //   * @return 유저 게시글 댓글 리스트
  //   */
  //  public List<UserArticleComment> getUserArticleComments(long articleIndex) {
  //    return userArticleCommentRepository.findByUserArticle_ArticleIndex(articleIndex);
  //  }
}
