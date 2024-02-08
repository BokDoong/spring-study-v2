package com.spring.mystudy.user.infra.http.dto;

import com.spring.mystudy.user.application.dto.request.TokenReissueCommand;
import com.spring.mystudy.user.application.dto.request.UserJoinCommand;
import com.spring.mystudy.user.application.dto.request.UserLoginCommand;
import com.spring.mystudy.user.infra.http.dto.request.TokenReissueDto;
import com.spring.mystudy.user.infra.http.dto.request.UserJoinDto;
import com.spring.mystudy.user.infra.http.dto.request.UserLoginDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserLoginCommand toCommand(UserLoginDto userLoginDto);
    UserJoinCommand toCommand(UserJoinDto userJoinDto);

    TokenReissueCommand toCommand(TokenReissueDto tokenReissueDto);
}
