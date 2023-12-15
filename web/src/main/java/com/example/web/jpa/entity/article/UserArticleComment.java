package com.example.web.jpa.entity.article;

import com.example.web.jpa.entity.user.UserInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "T_User_ArticleComment", indexes = @Index(
    name = "Index_ContentCreatedAtCreatedBy",
    columnList = "Content, CreatedAt, CreatedBy"))
@EqualsAndHashCode(of = "commentIndex") // commentIndex로 동등성 비교한다.
public class UserArticleComment extends ArticleBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CommentIndex")
  private Long commentIndex;

  @JoinColumn(name = "UserIndex")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserInfo userInfo;

  @JoinColumn(name = "ArticleIndex")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserArticle userArticle;

  @Column(name = "Content", length = 500, nullable = false)
  private String content;

  private UserArticleComment(UserInfo userInfo, UserArticle article, String content) {
    this.userInfo = userInfo;
    this.userArticle = article;
    this.content = content;
  }

  public static UserArticleComment of(UserInfo userInfo, UserArticle article, String content) {
    return new UserArticleComment(userInfo, article, content);
  }
}
