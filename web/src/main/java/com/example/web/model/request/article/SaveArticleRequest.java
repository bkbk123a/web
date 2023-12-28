package com.example.web.model.request.article;

import com.example.web.dto.article.UserArticleDto;
import com.example.web.jpa.entity.user.UserInfo;

public record SaveArticleRequest(
    String title,

    String content,

    String hashtag
) {

  public UserArticleDto toDto(UserInfo userInfo) {
    return UserArticleDto.of(title, content, hashtag, userInfo);
  }
}
