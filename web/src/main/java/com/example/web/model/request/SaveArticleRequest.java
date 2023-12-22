package com.example.web.model.request;

public record SaveArticleRequest(
    String title,

    String content,

    String hashtag
) {

}
