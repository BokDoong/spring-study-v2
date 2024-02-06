package com.spring.mystudy.user.infra.http;

import com.spring.mystudy.user.application.LoginService;
import com.spring.mystudy.user.infra.http.dto.UserDtoMapper;
import com.spring.mystudy.user.infra.http.dto.request.UserLoginDto;
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
    public String login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return loginService.login(mapper.toCommand(userLoginDto));
    }

}
