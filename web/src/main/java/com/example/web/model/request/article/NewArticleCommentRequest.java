package com.example.web.model.request.article;

public record NewArticleCommentRequest(
    long articleId,
    String content
) {

}
