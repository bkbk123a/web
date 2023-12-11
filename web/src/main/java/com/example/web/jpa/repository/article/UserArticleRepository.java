package com.example.web.jpa.repository.article;

import com.example.web.jpa.entity.article.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleRepository extends JpaRepository<UserArticle, Long> {

}
