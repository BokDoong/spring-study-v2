package com.spring.mystudy.mission.infra.http.dto;

import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand;
import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand.MissionCreateCommandBuilder;
import com.spring.mystudy.mission.infra.http.dto.request.MissionCreateDto;
import com.spring.mystudy.store.domain.location.Region;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:29:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class MissionDtoMapperImpl implements MissionDtoMapper {

    @Override
    public MissionCreateCommand toCommand(MissionCreateDto missionCreateDto) {
        if ( missionCreateDto == null ) {
            return null;
        }

        MissionCreateCommandBuilder missionCreateCommand = MissionCreateCommand.builder();

        missionCreateCommand.price( missionCreateDto.getPrice() );
        missionCreateCommand.content( missionCreateDto.getContent() );
        missionCreateCommand.deadline( missionCreateDto.getDeadline() );

        return missionCreateCommand.build();
    }
}
