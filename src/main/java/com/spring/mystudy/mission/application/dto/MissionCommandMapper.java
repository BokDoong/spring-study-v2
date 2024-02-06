package com.spring.mystudy.mission.application.dto;

import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand;
import com.spring.mystudy.mission.domain.Mission;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.location.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = Region.class)
public interface MissionCommandMapper {

    MissionCommandMapper INSTANCE = Mappers.getMapper(MissionCommandMapper.class);

    Mission toEntity(Store store, MissionCreateCommand missionCreateCommand);
}

