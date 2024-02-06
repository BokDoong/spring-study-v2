package com.spring.mystudy.user.infra.http;

import com.spring.mystudy.user.application.UserCommandService;
import com.spring.mystudy.user.infra.http.dto.UserDtoMapper;
import com.spring.mystudy.user.infra.http.dto.request.UserJoinDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserCommandService userCommandService;

    @PostMapping()
    public void join(@RequestBody @Valid UserJoinDto userJoinDto) {
        userCommandService.join(userDtoMapper.toCommand(userJoinDto));
    }
}
