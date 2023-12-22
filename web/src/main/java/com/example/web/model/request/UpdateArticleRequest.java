package com.example.web.model.request;

public record UpdateArticleRequest(
    String title,

    String content,

    String hashtag
) {

}
