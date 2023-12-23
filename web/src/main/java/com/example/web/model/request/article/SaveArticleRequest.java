package com.example.web.model.request.article;

public record SaveArticleRequest(
    String title,

    String content,

    String hashtag
) {

}
