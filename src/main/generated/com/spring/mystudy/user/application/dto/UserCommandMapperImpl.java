package com.spring.mystudy.user.application.dto;

import com.spring.mystudy.user.application.dto.request.UserJoinCommand;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.User.UserBuilder;
import com.spring.mystudy.user.domain.info.Category;
import com.spring.mystudy.user.domain.info.UserPrefer;
import com.spring.mystudy.user.domain.info.UserPrefer.UserPreferBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T17:03:52+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class UserCommandMapperImpl implements UserCommandMapper {

    @Override
    public User toEntity(UserJoinCommand userJoinCommand) {
        if ( userJoinCommand == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( userJoinCommand.getName() );
        user.nickname( userJoinCommand.getNickname() );
        user.email( userJoinCommand.getEmail() );
        user.password( userJoinCommand.getPassword() );
        user.phoneNumber( userJoinCommand.getPhoneNumber() );
        user.genderId( userJoinCommand.getGenderId() );
        user.birthDate( userJoinCommand.getBirthDate() );
        user.firstAddress( userJoinCommand.getFirstAddress() );
        user.secondAddress( userJoinCommand.getSecondAddress() );

        return user.build();
    }

    @Override
    public UserPrefer toEntity(User user, Category category) {
        if ( user == null && category == null ) {
            return null;
        }

        UserPreferBuilder userPrefer = UserPrefer.builder();

        if ( user != null ) {
            userPrefer.user( user );
        }
        if ( category != null ) {
            userPrefer.category( category );
        }

        return userPrefer.build();
    }
}
