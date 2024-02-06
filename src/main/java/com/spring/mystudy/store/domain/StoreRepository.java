package com.spring.mystudy.store.domain;

import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.domain.review.Review;

import java.util.Optional;

public interface StoreRepository {
    Optional<Store> findById(long storeId);

    Optional<Region> findRegion(String name);

    Region save(Region newRegion);

    void save(Store store);

    void save(Review review);
}
