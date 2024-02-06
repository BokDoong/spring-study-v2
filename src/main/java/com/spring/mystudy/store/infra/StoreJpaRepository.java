package com.spring.mystudy.store.infra;

import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.StoreRepository;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.domain.review.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreJpaRepository implements StoreRepository {

    private final EntityManager em;

    @Override
    public Optional<Store> findById(long storeId) {
        return Optional.ofNullable(em.find(Store.class, storeId));
    }

    @Override
    public Optional<Region> findRegion(String name) {
        List<Region> regions = em.createQuery("select r from Region r" +
                        " where r.name = :regionName", Region.class
                )
                .setParameter("regionName", name)
                .getResultList();

        return regions.stream().findAny();
    }

    @Override
    public Region save(Region newRegion) {
        em.persist(newRegion);

        return newRegion;
    }

    @Override
    public void save(Store store) {
        em.persist(store);
    }

    @Override
    public void save(Review review) {
        em.persist(review);
    }
}
