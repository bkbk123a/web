package com.example.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
@Controller
public class ArticleController {

  @Operation(summary = "게시글 전체 조회")
  @GetMapping
  public String getArticles(ModelMap map) {
    map.addAttribute("articles", List.of());

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
