package com.example.web.controller.article;

import com.example.web.dto.article.UserArticleCommentDto;
import com.example.web.dto.security.CustomUserDetails;
import com.example.web.model.request.article.NewArticleCommentRequest;
import com.example.web.service.article.ArticleCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "article-comment", description = "게시판 댓글 관련")
@RequiredArgsConstructor
@RequestMapping("/article-comments")
@Controller
public class ArticleCommentController {

  private final ArticleCommentService articleCommentService;

  @Operation(summary = "댓글 생성",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @PostMapping("/new")
  public String postNewArticleComment(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      NewArticleCommentRequest request) {

    UserArticleCommentDto dto = request.toDto(customUserDetails.toUserInfo());

    articleCommentService.saveArticleComment(dto);

    return "redirect:/articles/" + request.articleIndex();
  }

  @Operation(summary = "댓글 삭제",
      responses = @ApiResponse(description = "렌더링된 뷰 응답", responseCode = "200"))
  @PostMapping ("/{commentIndex}/delete")
  public String deleteArticleComment(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      @Parameter(description = "댓글 ID") @PathVariable Long commentIndex,
      @Parameter(description = "게시글 ID") Long articleIndex) {

    articleCommentService.deleteArticleComment(commentIndex, customUserDetails.userIndex());

    return "redirect:/articles/" + articleIndex;
  }
}