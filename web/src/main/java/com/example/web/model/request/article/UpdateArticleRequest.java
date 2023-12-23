package com.example.web.model.request.article;

public record UpdateArticleRequest(
    String title,

    String content,

    String hashtag
) {

}
