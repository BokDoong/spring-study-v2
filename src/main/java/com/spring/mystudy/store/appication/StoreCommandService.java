package com.spring.mystudy.store.appication;

import com.spring.mystudy.exception.BusinessException;
import com.spring.mystudy.exception.NotFoundException;
import com.spring.mystudy.exception.code.ErrorCode;
import com.spring.mystudy.store.appication.dto.StoreCommandMapper;
import com.spring.mystudy.store.appication.dto.request.ReviewCreateCommand;
import com.spring.mystudy.store.appication.dto.request.StoreCreateCommand;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.StoreRepository;
import com.spring.mystudy.store.domain.location.Region;
import com.spring.mystudy.store.domain.review.Review;
import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.UserRepository;
import com.spring.mystudy.user.domain.info.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandService {

    private final StoreCommandMapper storeCommandMapper;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(StoreCreateCommand storeCreateCommand) {
        // 1. 주소 찾기 -> 없으면 추가
        Region region = findOrCreateRegion(storeCreateCommand.getRegionAddress());
        // 2. Region + Category(찾아서) 생성
        Category category = findFoodCategory(storeCreateCommand.getCategoryId());
        // 3. save
        createAndSaveStore(storeCreateCommand, region, category);
    }

    @Transactional
    public void review(long userId, long storeId, ReviewCreateCommand reviewCreateCommand) {
        // 1. 회원 찾기
        User user = findUser(userId);
        // 2. 가게 찾기
        Store store = findStore(storeId);
        // 3. 리뷰 달기
        createAndSaveReview(reviewCreateCommand, user, store);
    }

    private void createAndSaveReview(ReviewCreateCommand reviewCreateCommand, User user, Store store) {
        storeRepository.save(createReview(reviewCreateCommand, user, store));
    }

    private Review createReview(ReviewCreateCommand reviewCreateCommand, User user, Store store) {
        Review review = storeCommandMapper.toEntity(reviewCreateCommand, user, store);
        user.addReview(review);
        store.addReview(review);

        return review;
    }

    private Store findStore(long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.STORE_NOT_FOUND, storeId));
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND, userId));
    }

    private void createAndSaveStore(StoreCreateCommand storeCreateCommand, Region region, Category category) {
        Store store = createStore(storeCreateCommand, region, category);
        storeRepository.save(store);
    }

    private Store createStore(StoreCreateCommand storeCreateCommand, Region region, Category category) {
        Store store = storeCommandMapper.toEntity(storeCreateCommand, category, region);
        region.addStore(store);
        category.addStore(store);
        return store;
    }

    private Region findOrCreateRegion(String regionName) {
        Optional<Region> region = findRegion(regionName);
        return region.orElseGet(() -> saveRegion(new Region(regionName)));
    }

    private Optional<Region> findRegion(String regionName) {
        return storeRepository.findRegion(regionName);
    }

    private Region saveRegion(Region region) {
        return storeRepository.save(region);
    }

    private Category findFoodCategory(Long categoryId) {
        return userRepository.findCategoryById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}
