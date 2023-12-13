package com.example.web.controller;

import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.model.enums.SearchType;
import com.example.web.service.article.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

  private final ArticleService articleService;

  @Operation(summary = "게시글 전체 조회(메인 화면)")
  @GetMapping
  public String getArticles(
      @RequestParam(required = false) SearchType searchType,
      @RequestParam(required = false) String searchKeyWord,
      @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
      ModelMap map) {

    Page<UserArticle> userArticles = articleService
        .getUserArticles(searchType, searchKeyWord, pageable);

    map.addAttribute("articles", userArticles);

    return "articles/index";
  }

  @Operation(summary = "게시글 상세 조회")
  @GetMapping("/{articleId}")
  public String getArticle(@PathVariable Long articleId, ModelMap map) {
    map.addAttribute("article", "article"); // TODO: 실제 데이터
    map.addAttribute("articleComments", List.of());

    return "articles/detail";
  }
}
