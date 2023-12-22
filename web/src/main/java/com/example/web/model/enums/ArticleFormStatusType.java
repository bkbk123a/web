package com.example.web.model.enums;

import lombok.Getter;

public enum ArticleFormStatusType {
  CREATE("저장", false),
  UPDATE("수정", true);

  @Getter
  private final String description;

  @Getter
  private final Boolean update;

  ArticleFormStatusType(String description, Boolean update) {
    this.description = description;
    this.update = update;
  }
}
