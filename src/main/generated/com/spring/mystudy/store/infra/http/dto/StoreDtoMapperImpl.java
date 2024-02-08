package com.spring.mystudy.store.infra.http.dto;

import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand;
import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand.ReviewCreateCommandBuilder;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand.StoreCreateCommandBuilder;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.infra.http.dto.request.ReviewCreateDto;
import com.spring.mystudy.store.infra.http.dto.request.StoreRegisterDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-08T17:42:23+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class StoreDtoMapperImpl implements StoreDtoMapper {

    @Override
    public StoreCreateCommand toCommand(StoreRegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        StoreCreateCommandBuilder storeCreateCommand = StoreCreateCommand.builder();

        storeCreateCommand.name( registerDto.getName() );
        storeCreateCommand.openTime( registerDto.getOpenTime() );
        storeCreateCommand.closeTime( registerDto.getCloseTime() );
        storeCreateCommand.firstAddress( registerDto.getFirstAddress() );
        storeCreateCommand.secondAddress( registerDto.getSecondAddress() );
        List<Long> list = registerDto.getCategoryIds();
        if ( list != null ) {
            storeCreateCommand.categoryIds( new ArrayList<Long>( list ) );
        }

        storeCreateCommand.regionAddress( Region.extractTown(registerDto.getFirstAddress()) );

        return storeCreateCommand.build();
    }

    @Override
    public ReviewCreateCommand toCommand(ReviewCreateDto reviewCreateDto) {
        if ( reviewCreateDto == null ) {
            return null;
        }

        ReviewCreateCommandBuilder reviewCreateCommand = ReviewCreateCommand.builder();

        reviewCreateCommand.content( reviewCreateDto.getContent() );
        reviewCreateCommand.rating( reviewCreateDto.getRating() );

        return reviewCreateCommand.build();
    }
}
