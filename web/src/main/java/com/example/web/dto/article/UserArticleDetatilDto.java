package com.example.web.dto.article;

import com.example.web.jpa.entity.article.UserArticle;
import com.example.web.jpa.entity.article.UserArticleComment;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class UserArticleDetatilDto {
  private UserArticle userArticle;
  private Set<UserArticleComment> userArticleComments = new HashSet<>();
}
