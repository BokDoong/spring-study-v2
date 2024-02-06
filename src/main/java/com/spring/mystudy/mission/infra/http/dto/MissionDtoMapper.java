package com.spring.mystudy.mission.infra.http.dto;

import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand;
import com.spring.mystudy.mission.infra.http.dto.request.MissionCreateDto;
import com.spring.mystudy.store.domain.location.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = Region.class)
public interface MissionDtoMapper {

    MissionDtoMapper INSTANCE = Mappers.getMapper(MissionDtoMapper.class);

    MissionCreateCommand toCommand(MissionCreateDto missionCreateDto);
}
