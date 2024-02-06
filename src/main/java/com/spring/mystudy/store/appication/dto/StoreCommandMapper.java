package com.spring.mystudy.store.appication.dto;

import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.domain.review.Review;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.info.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StoreCommandMapper {
    StoreCommandMapper INSTANCE = Mappers.getMapper(StoreCommandMapper.class);

    @Mapping(target = "name", source = "storeCreateCommand.name")
    Store toEntity(StoreCreateCommand storeCreateCommand, Category category, Region region);

    Review toEntity(ReviewCreateCommand reviewCreateCommand, User user, Store store);
}
