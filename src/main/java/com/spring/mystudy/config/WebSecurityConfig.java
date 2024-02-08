package com.spring.mystudy.config;

import com.spring.mystudy.config.jwt.*;
import com.spring.mystudy.config.jwt.handler.JwtAccessDeniedHandler;
import com.spring.mystudy.config.jwt.handler.JwtAuthenticationEntryPoint;
import com.spring.mystudy.config.jwt.utils.JwtExtractor;
import com.spring.mystudy.config.jwt.utils.JwtVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtExtractor jwtExtractor;
    private final JwtVerifier jwtVerifier;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {"/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/api/v1/users/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF, CORS
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        // STATELESS 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // FormLogin, BasicHttp 비활성화
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        // JwtAuthFilter 추가
        http.addFilterBefore(new JwtAuthFilter(jwtExtractor, jwtVerifier), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling((exceptionHandling) -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );

        // 권한 규칙 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AUTH_WHITELIST).permitAll()
                // @PreAuthorization -> 어노테이션으로 메서드마다 설정 -> 이때는 .permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
