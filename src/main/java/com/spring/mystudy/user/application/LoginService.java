package com.spring.mystudy.user.application;

import com.spring.mystudy.config.auth.AuthenticatedUserInfo;
import com.spring.mystudy.config.jwt.JwtUtil;
import com.spring.mystudy.exception.BusinessException;
import com.spring.mystudy.exception.code.ErrorCode;
import com.spring.mystudy.user.application.dto.request.UserLoginCommand;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public String login(UserLoginCommand loginCommand) {
        User user = findUser(loginCommand);
        verifyPasswords(loginCommand, user);
        return jwtUtil.createAccessToken(new AuthenticatedUserInfo(user));
    }

    private void verifyPasswords(UserLoginCommand loginCommand, User user) {
        if (!encoder.matches(loginCommand.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "비밀번호가 일치하지 않습니다.");
        }
    }

    private User findUser(UserLoginCommand loginCommand) {
        return userRepository.findByEmail(loginCommand.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, "일치하는 이메일의 유저가 없습니다."));
    }
}
