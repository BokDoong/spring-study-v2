package com.spring.mystudy.store.appication.dto;

import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.Store.StoreBuilder;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.domain.review.Review;
import com.spring.mystudy.store.domain.review.Review.ReviewBuilder;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.info.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:29:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class StoreCommandMapperImpl implements StoreCommandMapper {

    @Override
    public Store toEntity(StoreCreateCommand storeCreateCommand, Category category, Region region) {
        if ( storeCreateCommand == null && category == null && region == null ) {
            return null;
        }

        StoreBuilder store = Store.builder();

        if ( storeCreateCommand != null ) {
            store.name( storeCreateCommand.getName() );
            store.openTime( storeCreateCommand.getOpenTime() );
            store.closeTime( storeCreateCommand.getCloseTime() );
            store.firstAddress( storeCreateCommand.getFirstAddress() );
            store.secondAddress( storeCreateCommand.getSecondAddress() );
        }
        if ( category != null ) {
            store.category( category );
        }
        if ( region != null ) {
            store.region( region );
        }

        return store.build();
    }

    @Override
    public Review toEntity(ReviewCreateCommand reviewCreateCommand, User user, Store store) {
        if ( reviewCreateCommand == null && user == null && store == null ) {
            return null;
        }

        ReviewBuilder review = Review.builder();

        if ( reviewCreateCommand != null ) {
            review.content( reviewCreateCommand.getContent() );
            review.rating( reviewCreateCommand.getRating() );
        }
        if ( user != null ) {
            review.user( user );
        }
        if ( store != null ) {
            review.store( store );
        }

        return review.build();
    }
}
