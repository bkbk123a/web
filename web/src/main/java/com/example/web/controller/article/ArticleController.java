package com.example.web.controller.article;

import com.example.web.dto.article.UserArticleDetatilDto;
import com.example.web.dto.article.UserArticleDto;
import com.example.web.dto.security.CustomUserDetails;
import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.model.enums.ArticleFormStatusType;
import com.example.web.model.enums.SearchType;
import com.example.web.model.request.article.SaveArticleRequest;
import com.example.web.model.request.article.UpdateArticleRequest;
import com.example.web.service.PageService;
import com.example.web.service.article.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "article", description = "게시판 관련")
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

  private final ArticleService articleService;
  private final PageService pageService;

  @Operation(summary = "게시글 전체 조회(메인 화면)",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @GetMapping
  public String getArticles(
      @Parameter(description = "조회 방식") @RequestParam(required = false) SearchType searchType,
      @Parameter(description = "조회할 내용") @RequestParam(required = false) String searchValue,
      @Parameter(description = "페이징 관련") @PageableDefault(size = 10, sort = "createdAt",
          direction = Sort.Direction.DESC) Pageable pageable, ModelMap map) {

    Page<UserArticle> userArticles = articleService.getUserArticles(
        searchType, searchValue, pageable);

    List<Integer> barNumbers = pageService.getPageBarNumbers(
        pageable.getPageNumber(), userArticles.getTotalPages());

    map.addAttribute("articles", userArticles);
    map.addAttribute("pageBarNumbers", barNumbers);
    map.addAttribute("searchTypes", SearchType.values());

    return "articles/index";
  }

  @Operation(summary = "게시글 상세 조회",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @GetMapping("/{articleId}")
  public String getArticle(@Parameter(description = "게시글 ID") @PathVariable long articleId,
      ModelMap map) {

    UserArticleDetatilDto userArticleDetatilDto = articleService
        .getUserArticleDetail(articleId);

    map.addAttribute("article", userArticleDetatilDto.getUserArticle());
    map.addAttribute("articleComments", userArticleDetatilDto.getUserArticleComments());
    map.addAttribute("totalCount", articleService.getUserArticleCount());

    return "articles/detail";
  }

  @Operation(summary = "해시태그 항목 조회",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @GetMapping("/search-hashtag")
  public String getArticlesByHashtag(
      @Parameter(description = "조회할 내용") @RequestParam(required = false) String searchValue,
      @Parameter(description = "페이징 관련") @PageableDefault(size = 10, sort = "createdAt",
          direction = Sort.Direction.DESC) Pageable pageable, ModelMap map) {

    Page<UserArticle> userArticles = articleService.getUserArticlesByHashtag(searchValue, pageable);

    List<Integer> barNumbers = pageService.getPageBarNumbers(
        pageable.getPageNumber(), userArticles.getTotalPages());

    List<String> hashtags = articleService.getDistinctHashtags();

    map.addAttribute("articles", userArticles);
    map.addAttribute("pageBarNumbers", barNumbers);
    map.addAttribute("hashtags", hashtags);
    map.addAttribute("searchType", SearchType.HASHTAG);

    return "articles/search-hashtag";
  }

  @Operation(summary = "글쓰기 버튼 눌렀을 시 새 게시글 기본 폼(View) 응답",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @GetMapping("/form")
  public String articleForm(ModelMap map) {
    map.addAttribute("formStatus", ArticleFormStatusType.CREATE);

    return "articles/form";
  }

  @Operation(summary = "저장 버튼 눌렀을 시 새 게시글 저장",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @PostMapping("/form")
  public String postNewArticle(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      SaveArticleRequest saveArticleRequest) {

    UserArticleDto dto = saveArticleRequest.toDto(customUserDetails.toUserInfo());

    articleService.saveArticle(dto);

    return "redirect:/articles";
  }

  @Operation(summary = "수정 버튼 눌렀을 시 게시글 수정 기본 폼(View) 응답",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @GetMapping("/{articleId}/form")
  public String updateArticleForm(
      @Parameter(description = "게시글 ID") @PathVariable Long articleId,
      ModelMap map) {
    UserArticle userArticle = articleService.getUserArticleOrElseThrow(articleId);

    map.addAttribute("article", userArticle);
    map.addAttribute("formStatus", ArticleFormStatusType.UPDATE);

    return "articles/form";
  }

  @Operation(summary = "수정 버튼 눌렀을 시 특정 게시글 수정",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @PostMapping("/{articleId}/form")
  public String updateArticle(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      @Parameter(description = "게시글 ID") @PathVariable Long articleId,
      UpdateArticleRequest updateArticleRequest) {

    UserArticleDto userArticleDto = updateArticleRequest.toDto(customUserDetails.toUserInfo());

    articleService.updateArticle(articleId, userArticleDto);

    return "redirect:/articles/" + articleId;
  }

  @Operation(summary = "삭제 버튼 눌렀을 시 특정 게시글 삭제",
      responses = @ApiResponse(description = "렌더링 된 뷰 응답", responseCode = "200"))
  @PostMapping("/{articleId}/delete")
  public String deleteArticle(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      @Parameter(description = "게시글 ID") @PathVariable Long articleId) {
    // 로그인 인증한 유저가 본인이 작성한 글을 삭제 한다.
    articleService.deleteArticle(articleId, customUserDetails.userIndex());

    return "redirect:/articles";
  }
}
