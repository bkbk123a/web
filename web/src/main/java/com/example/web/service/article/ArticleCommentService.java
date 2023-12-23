package com.example.web.service.article;

import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.entity.article.UserArticleComment;
import com.example.web.jpa.repository.article.UserArticleCommentRepository;
import com.example.web.model.request.article.NewArticleCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

  private final ArticleService articleService;
  private final UserArticleCommentRepository userArticleCommentRepository;

  public void saveArticleComment(NewArticleCommentRequest request) {
    UserArticle userArticle = articleService.getUserArticleOrElseThrow(request.articleId());

    UserArticleComment userArticleComment = UserArticleComment.builder()
        .createUserIndex(userArticle.getUserInfo().getUserIndex())
        .userArticle(userArticle)
        .userInfo(userArticle.getUserInfo())
        .content(request.content())
        .build();

    userArticleCommentRepository.save(userArticleComment);
  }

  public void deleteArticleComment(Long articleCommentId) {
    userArticleCommentRepository.deleteById(articleCommentId);
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
