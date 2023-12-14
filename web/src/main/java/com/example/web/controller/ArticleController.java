package com.example.web.controller;

import com.example.web.dto.article.UserArticleDetatilDto;
import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.model.enums.SearchType;
import com.example.web.service.PageService;
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
  private final PageService pageService;

  @Operation(summary = "게시글 전체 조회(메인 화면)")
  @GetMapping
  public String getArticles(
      @RequestParam(required = false) SearchType searchType,
      @RequestParam(required = false) String searchKeyWord,
      @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
      ModelMap map) {

    Page<UserArticle> userArticles = articleService.getUserArticles(
        searchType, searchKeyWord, pageable);

    List<Integer> barNumbers = pageService.getPageBarNumbers(
        pageable.getPageNumber(), userArticles.getTotalPages());

    map.addAttribute("articles", userArticles);
    map.addAttribute("pageBarNumbers", barNumbers);

    return "articles/index";
  }

  @Operation(summary = "게시글 상세 조회")
  @GetMapping("/{articleId}")
  public String getArticle(@PathVariable long articleId, ModelMap map) {

    UserArticleDetatilDto userArticleDetatilDto = articleService
        .getUserArticleDetail(articleId);

    map.addAttribute("article", userArticleDetatilDto.getUserArticle());
    map.addAttribute("articleComments", userArticleDetatilDto.getUserArticleComments());

    return "articles/detail";
  }
}
