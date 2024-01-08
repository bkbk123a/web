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
import org.hibernate.annotations.Comment;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "T_User_ArticleComment", indexes = @Index(
    name = "Index_ContentCreatedAtUserIndex",
    columnList = "Content, CreatedAt, UserIndex"))
@EqualsAndHashCode(of = "commentIndex") // commentIndex로 동등성 비교한다.
public class UserArticleComment extends ArticleBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CommentIndex")
  private Long commentIndex;

  @JoinColumn(name = "UserIndex")
  @Comment("생성한 유저 인덱스")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserInfo userInfo;

  @JoinColumn(name = "ArticleIndex")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserArticle userArticle;

  @Column(name = "Content", length = 500, nullable = false)
  private String content;

  private UserArticleComment(String content, UserInfo userInfo, UserArticle article) {
    this.content = content;
    this.userInfo = userInfo;
    this.userArticle = article;
  }

  public static UserArticleComment of( String content, UserInfo userInfo, UserArticle article) {
    return new UserArticleComment(content, userInfo, article);
  }
}
