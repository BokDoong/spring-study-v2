package com.spring.mystudy.user.application;

import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> findUserForOAuth(String email) {
        return userRepository.findByEmail(email);
    }
}
