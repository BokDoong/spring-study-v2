package com.spring.mystudy.user.application.dto;

import com.spring.mystudy.config.auth.AuthenticatedUserInfo;
import com.spring.mystudy.user.application.dto.request.UserJoinCommand;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.info.Category;
import com.spring.mystudy.user.domain.info.UserPrefer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserCommandMapper {

    UserCommandMapper INSTANCE = Mappers.getMapper(UserCommandMapper.class);

    User toEntity(UserJoinCommand userJoinCommand);

    UserPrefer toEntity(User user, Category category);

    default List<UserPrefer> toEntities(User user, List<Category> foodCategories) {
        return foodCategories.stream()
                .map(foodCategory -> createUserPrefer(user, foodCategory))
                .collect(Collectors.toList());
    }

    private UserPrefer createUserPrefer(User user, Category foodCategory) {
        return UserPrefer.builder()
                .user(user)
                .category(foodCategory)
                .build();
    }
}
