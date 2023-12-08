package com.example.web.controller;

import com.example.web.util.advice.ResponseAdvice;
import com.example.web.util.interceptor.SessionInterceptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(value = ArticleController.class)
class ArticleControllerTest {

  @MockBean
  private SessionInterceptor sessionInterceptor;

  @MockBean
  private ResponseAdvice responseAdvice;

  @Autowired
  private MockMvc mockMvc;

  @Disabled("구현 중")
  @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
    // Given

    // When & Then
    mockMvc.perform(get("/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("articles/index"))
        .andExpect(model().attributeExists("articles"));
  }

  @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
  @Test
  public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
    // Given

    // When & Then
    mockMvc.perform(get("/articles/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("articles/detail"))
        .andExpect(model().attributeExists("article"))
        .andExpect(model().attributeExists("articleComments"));
  }
}