package com.example.web.service.user;

import com.example.web.jpa.entity.UserInfo;
import com.example.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfo getUserInfo(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress)
                .orElseGet(() -> UserInfo.builder()
                        .userIndex(-1L)
                        .build());
    }

    public UserInfo getUserInfo(Long userIndex) {
        return userRepository.findById(userIndex)
                .orElseGet(() -> UserInfo.builder()
                        .userIndex(-1L)
                        .build());
    }
}
