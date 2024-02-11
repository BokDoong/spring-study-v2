package com.spring.mystudy.user.application;

import com.spring.mystudy.config.jwt.factory.JwtFactory;
import com.spring.mystudy.config.jwt.utils.JwtExtractor;
import com.spring.mystudy.config.jwt.utils.JwtVerifier;
import com.spring.mystudy.exception.BusinessException;
import com.spring.mystudy.exception.NotFoundException;
import com.spring.mystudy.exception.code.ErrorCode;
import com.spring.mystudy.user.application.dto.request.TokenReissueCommand;
import com.spring.mystudy.user.application.dto.request.UserLoginCommand;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.UserRepository;
import com.spring.mystudy.user.infra.http.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtFactory jwtFactory;
    private final JwtExtractor jwtExtractor;
    private final JwtVerifier jwtVerifier;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public TokenResponse login(UserLoginCommand loginCommand) {
        User user = findUserWithEmail(loginCommand.getEmail());
        verifyPasswords(loginCommand, user);
        return toTokenResponse(user.getId());
    }

    @Transactional
    public TokenResponse reissue(TokenReissueCommand tokenReissueCommand) {
        Long userId = jwtExtractor.getUserId(tokenReissueCommand.accessToken());
        jwtVerifier.verifyRefreshToken(userId, tokenReissueCommand.refreshToken());
        return toTokenResponse(userId);
    }

    @Transactional
    public void logout(long userId) {
        User user = findUser(userId);
        jwtVerifier.expireRefreshToken(user.getId());
    }

    private void verifyPasswords(UserLoginCommand loginCommand, User user) {
        if (!encoder.matches(loginCommand.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "비밀번호가 일치하지 않습니다.");
        }
    }

    private TokenResponse toTokenResponse(long userId) {
        return TokenResponse.builder()
                .accessToken(jwtFactory.createAccessToken(userId))
                .refreshToken(jwtFactory.createRefreshToken(userId))
                .build();
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND, userId));
    }

    private User findUserWithEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND, "일치하는 이메일의 유저가 없습니다."));
    }
}
