package com.spring.mystudy.user.infra.http.dto;

import com.spring.mystudy.user.application.dto.request.UserJoinCommand;
import com.spring.mystudy.user.infra.http.dto.request.UserJoinDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserJoinCommand toCommand(UserJoinDto userJoinDto);
}
