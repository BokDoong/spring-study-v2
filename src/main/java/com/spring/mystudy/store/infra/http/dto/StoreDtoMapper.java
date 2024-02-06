package com.spring.mystudy.store.infra.http.dto;

import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.infra.http.dto.request.ReviewCreateDto;
import com.spring.mystudy.store.infra.http.dto.request.StoreRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = Region.class)
public interface StoreDtoMapper {
    StoreDtoMapper INSTANCE = Mappers.getMapper(StoreDtoMapper.class);

    @Mapping(target = "regionAddress", expression = "java(Region.extractTown(registerDto.getFirstAddress()))")
    StoreCreateCommand toCommand(StoreRegisterDto registerDto);

    ReviewCreateCommand toCommand(ReviewCreateDto reviewCreateDto);

}
