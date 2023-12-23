package com.example.web.controller;

import com.example.web.model.request.article.NewArticleCommentRequest;
import com.example.web.service.article.ArticleCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/article-comments")
@Controller
public class ArticleCommentController {

  private final ArticleCommentService articleCommentService;

  @Operation(summary = "댓글 생성")
  @PostMapping("/new")
  public String postNewArticleComment(NewArticleCommentRequest request) {
    // TODO: 인증 정보를 넣어줘야 한다.
    articleCommentService.saveArticleComment(request);

    return "redirect:/articles/" + request.articleId();
  }

  @Operation(summary = "댓글 삭제")
  @PostMapping ("/{commentId}/delete")
  public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
    articleCommentService.deleteArticleComment(commentId);

    return "redirect:/articles/" + articleId;
  }
}