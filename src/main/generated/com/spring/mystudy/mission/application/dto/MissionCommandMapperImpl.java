package com.spring.mystudy.mission.application.dto;

import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand;
import com.spring.mystudy.mission.domain.Mission;
import com.spring.mystudy.mission.domain.Mission.MissionBuilder;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.location.Region;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T15:08:48+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class MissionCommandMapperImpl implements MissionCommandMapper {

    @Override
    public Mission toEntity(Store store, MissionCreateCommand missionCreateCommand) {
        if ( store == null && missionCreateCommand == null ) {
            return null;
        }

        MissionBuilder mission = Mission.builder();

        if ( store != null ) {
            mission.store( store );
        }
        if ( missionCreateCommand != null ) {
            mission.content( missionCreateCommand.getContent() );
            mission.price( missionCreateCommand.getPrice() );
            mission.deadline( missionCreateCommand.getDeadline() );
        }

        return mission.build();
    }
}
