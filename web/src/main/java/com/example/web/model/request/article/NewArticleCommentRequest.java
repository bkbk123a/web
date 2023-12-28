package com.example.web.model.request.article;

import com.example.web.dto.article.UserArticleCommentDto;
import com.example.web.jpa.entity.user.UserInfo;

public record NewArticleCommentRequest(
    long articleIndex,
    String content
) {

  public UserArticleCommentDto toDto(UserInfo userInfo) {
    return UserArticleCommentDto.of(articleIndex, content, userInfo);
  }
}
