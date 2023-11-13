package com.example.web.service;

import com.example.web.model.exception.CustomErrorException;
import com.example.web.model.oauth.JwtUser;
import com.example.web.util.container.SessionContainer;

public abstract class ServiceBase {

  protected JwtUser getSessions() {
    return SessionContainer.getSession();
  }

  protected long getUserIndex() {
    JwtUser jwtUser = getSessions();
    if(jwtUser == null) {
      throw CustomErrorException.builder().resultValue(4).build();
    }

    return jwtUser.getUserIndex();
  }
}
