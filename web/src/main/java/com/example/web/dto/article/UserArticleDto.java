package com.example.web.dto.article;

import com.example.web.jpa.entity.user.UserInfo;

public record UserArticleDto(
    String title,
    String content,
    String hashtag,
    UserInfo userInfo
) {

  public static UserArticleDto of(String title, String content, String hashtag, UserInfo userInfo) {
    return new UserArticleDto(title, content, hashtag, userInfo);
  }
}