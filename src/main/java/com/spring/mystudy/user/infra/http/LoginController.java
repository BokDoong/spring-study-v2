package com.spring.mystudy.user.infra.http;

import com.spring.mystudy.user.application.LoginService;
import com.spring.mystudy.user.infra.http.dto.UserDtoMapper;
import com.spring.mystudy.user.infra.http.dto.request.TokenReissueDto;
import com.spring.mystudy.user.infra.http.dto.request.UserLoginDto;
import com.spring.mystudy.user.infra.http.dto.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class LoginController {

    private final LoginService loginService;
    private final UserDtoMapper mapper;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return loginService.login(mapper.toCommand(userLoginDto));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@Valid @RequestBody TokenReissueDto tokenReissueDto) {
        return loginService.reissue(mapper.toCommand(tokenReissueDto));
    }
}
