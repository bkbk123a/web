package com.example.web.jpa.entity.article;

import com.example.web.jpa.entity.user.UserInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "T_User_Article", indexes = @Index(
    name = "Index_TitleHashTagCreatedAtCreateUserIndex",
    columnList = "Title, HashTag, CreatedAt, CreateUserIndex"))
@EqualsAndHashCode(of = "articleIndex") // articleIndex로 동등성 비교한다.
public class UserArticle extends ArticleBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ArticleIndex")
  private Long articleIndex;

  @JoinColumn(name = "UserIndex")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)  // FK : not null
  private UserInfo userInfo;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Content", nullable = false, length = 10000)
  private String content; // 본문

  @Column(name = "HashTag")
  private String hashtag = "";

  @OrderBy("createdAt DESC")
  @OneToMany(mappedBy = "userArticle", cascade = CascadeType.ALL)
  private final Set<UserArticleComment> userArticleComments = new LinkedHashSet<>();

  private UserArticle(UserInfo userInfo, String title, String content, String hashtag) {
    this.userInfo = userInfo;
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
    this.setCreateUserIndex(userInfo.getUserIndex());
    this.setModiftUserIndex(userInfo.getUserIndex());
  }

  public static UserArticle of(UserInfo userInfo, String title, String content, String hashtag) {
    return new UserArticle(userInfo, title, content, hashtag);
  }

  public void modifyContent(long modifyUserIndex, String content) {
    this.setModiftUserIndex(modifyUserIndex);
    this.content = content;
  }
}
