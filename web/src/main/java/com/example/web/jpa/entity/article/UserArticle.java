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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "T_User_Article", indexes = @Index(
    name = "Index_TitleHashTagCreatedAtCreatedBy",
    columnList = "Title, HashTag, CreatedAt, CreatedBy"))
@EqualsAndHashCode(callSuper = false)
public class UserArticle extends ArticleBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ArticleIndex;

  @JoinColumn(name = "UserIndex")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserInfo userInfo;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Content", nullable = false, length = 10000)
  private String content; // 본문

  @Column(name = "HashTag")
  private String hashtag = "";

  private UserArticle(UserInfo userInfo, String title, String content, String hashtag, String createdBy) {
    this.userInfo = userInfo;
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
    this.setCreatedBy(createdBy);
    this.setModifiedBy(createdBy);
  }

  public static UserArticle of(UserInfo userInfo, String title, String content, String hashtag, String createdBy) {
    return new UserArticle(userInfo, title, content, hashtag, createdBy);
  }

  public void modifyContent(String modifyBy, String content) {
    this.setModifiedBy(modifyBy);
    this.content = content;
  }
}
