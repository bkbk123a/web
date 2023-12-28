package com.example.web.dto.article;

import com.example.web.jpa.entity.user.UserInfo;

public record UserArticleCommentDto(
    long articleIndex,
    String content,

    UserInfo userInfo
) {

  public static UserArticleCommentDto of(long articleIndex, String content, UserInfo userInfo) {
    return new UserArticleCommentDto(articleIndex, content, userInfo);
  }
}
