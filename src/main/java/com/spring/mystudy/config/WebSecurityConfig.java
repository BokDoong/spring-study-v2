package com.spring.mystudy.config;

import com.spring.mystudy.config.jwt.*;
import com.spring.mystudy.config.jwt.factory.JwtFactory;
import com.spring.mystudy.config.jwt.handler.JwtAccessDeniedHandler;
import com.spring.mystudy.config.jwt.handler.JwtAuthenticationEntryPoint;
import com.spring.mystudy.config.jwt.utils.JwtExtractor;
import com.spring.mystudy.config.jwt.utils.JwtVerifier;
import com.spring.mystudy.config.oauth2.OAuthFailureHandler;
import com.spring.mystudy.config.oauth2.OAuthSuccessHandler;
import com.spring.mystudy.config.oauth2.OAuthLoader;
import com.spring.mystudy.user.application.UserCommandService;
import com.spring.mystudy.user.application.UserQueryService;
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

    private final CorsConfig corsConfig;
    private final OAuthLoader oAuthLoader;
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final JwtExtractor jwtExtractor;
    private final JwtVerifier jwtVerifier;
    private final JwtFactory jwtFactory;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {"/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/login", "/oauth2/**", "/", "/favicon.ico", "/api/v1/users"};
    private static final String[] AUTH_BLACKLIST = {"/api/v1/stores**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF, CORS
        http.addFilter(corsConfig.getCorsFilter());
        http.csrf(AbstractHttpConfigurer::disable);

        // STATELESS 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // FormLogin, BasicHttp 비활성화
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 권한 규칙 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AUTH_BLACKLIST).authenticated()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll());

        // JwtAuthFilter 추가
        http.addFilterBefore(new JwtAuthFilter(jwtExtractor, jwtVerifier), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exceptionHandling) -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );

        // oauth
        http.oauth2Login(oauth2 -> oauth2
                .successHandler(new OAuthSuccessHandler(jwtFactory, userQueryService, userCommandService))
                .failureHandler(new OAuthFailureHandler())
                .userInfoEndpoint(userInfo -> userInfo.userService(oAuthLoader)));

        return http.build();
    }
}
