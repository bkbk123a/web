package com.example.web.service.user;

import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.user.UserRepository;
import com.example.web.model.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserInfo saveUserInfo(UserInfo userInfo) {
    return userRepository.save(userInfo);
  }

  public Optional<UserInfo> getUserInfoByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
