package com.spring.mystudy.user.infra.http.dto;

import com.spring.mystudy.user.application.dto.request.UserJoinCommand;
import com.spring.mystudy.user.application.dto.request.UserLoginCommand;
import com.spring.mystudy.user.application.dto.request.UserLoginCommand.UserLoginCommandBuilder;
import com.spring.mystudy.user.infra.http.dto.request.UserJoinDto;
import com.spring.mystudy.user.infra.http.dto.request.UserLoginDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T14:13:21+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserLoginCommand toCommand(UserLoginDto userLoginDto) {
        if ( userLoginDto == null ) {
            return null;
        }

        UserLoginCommandBuilder userLoginCommand = UserLoginCommand.builder();

        userLoginCommand.email( userLoginDto.getEmail() );
        userLoginCommand.password( userLoginDto.getPassword() );

        return userLoginCommand.build();
    }

    @Override
    public UserJoinCommand toCommand(UserJoinDto userJoinDto) {
        if ( userJoinDto == null ) {
            return null;
        }

        List<Long> categoryIds = null;
        String name = null;
        String nickname = null;
        String email = null;
        String password = null;
        String phoneNumber = null;
        Integer genderId = null;
        String firstAddress = null;
        String secondAddress = null;
        LocalDateTime birthDate = null;

        List<Long> list = userJoinDto.getCategoryIds();
        if ( list != null ) {
            categoryIds = new ArrayList<Long>( list );
        }
        name = userJoinDto.getName();
        nickname = userJoinDto.getNickname();
        email = userJoinDto.getEmail();
        password = userJoinDto.getPassword();
        phoneNumber = userJoinDto.getPhoneNumber();
        genderId = userJoinDto.getGenderId();
        firstAddress = userJoinDto.getFirstAddress();
        secondAddress = userJoinDto.getSecondAddress();
        birthDate = userJoinDto.getBirthDate();

        UserJoinCommand userJoinCommand = new UserJoinCommand( name, nickname, email, password, phoneNumber, genderId, categoryIds, firstAddress, secondAddress, birthDate );

        return userJoinCommand;
    }
}
