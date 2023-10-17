package com.example.web.service;

import com.example.web.model.oauth.JwtUser;
import com.example.web.util.container.SessionContainer;

public abstract class ServiceBase {

  protected JwtUser getSessions() {
    return SessionContainer.getSession();
  }

  protected long getUserIndex() {
    return getSessions().getUserIndex();
  }
}
