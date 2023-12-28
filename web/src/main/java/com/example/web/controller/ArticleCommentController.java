package com.example.web.controller;

import com.example.web.dto.article.UserArticleCommentDto;
import com.example.web.dto.security.CustomUserDetails;
import com.example.web.model.request.article.NewArticleCommentRequest;
import com.example.web.service.article.ArticleCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  public String postNewArticleComment(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      NewArticleCommentRequest request) {

    UserArticleCommentDto dto = request.toDto(customUserDetails.toUserInfo());

    articleCommentService.saveArticleComment(dto);

    return "redirect:/articles/" + request.articleIndex();
  }

  @Operation(summary = "댓글 삭제")
  @PostMapping ("/{commentIndex}/delete")
  public String deleteArticleComment(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      @PathVariable Long commentIndex, Long articleIndex) {

    articleCommentService.deleteArticleComment(commentIndex, customUserDetails.userIndex());

    return "redirect:/articles/" + articleIndex;
  }
}