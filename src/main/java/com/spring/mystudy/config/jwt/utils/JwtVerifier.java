package com.spring.mystudy.config.jwt.utils;

import com.spring.mystudy.config.jwt.JwtException;
import com.spring.mystudy.exception.NotFoundException;
import com.spring.mystudy.exception.code.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Optional;

import static com.spring.mystudy.config.jwt.JwtException.*;

@Slf4j
@Component
public class JwtVerifier {

    private final Key key;
    private final RedisTemplate<String, String> redisTemplate;

    public JwtVerifier(
            RedisTemplate<String, String> redisTemplate,
            @Value("${jwt.secret}") String secretKey
    ) {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
        this.redisTemplate = redisTemplate;
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            setAttribute(request, WRONG_TOKEN);
        } catch (ExpiredJwtException e) {
            setAttribute(request, EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            setAttribute(request, UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            setAttribute(request, EMPTY_TOKEN);
        }
        return false;
    }

    public void verifyRefreshToken(long userId, String refreshToken) {
        String storedRefreshToken = findRefreshToken(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TOKEN_NOT_FOUND));
        if(!storedRefreshToken.equals(refreshToken))
            throw new NotFoundException(ErrorCode.TOKEN_NOT_FOUND);
    }

    private Optional<String> findRefreshToken(long userId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(String.valueOf(userId)));
    }


    private void setAttribute(HttpServletRequest request, JwtException exception) {
        request.setAttribute("exception", exception.name());
    }
}
