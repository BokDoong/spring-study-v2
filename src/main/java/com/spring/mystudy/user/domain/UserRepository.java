package com.spring.mystudy.user.domain;

import com.spring.mystudy.user.domain.info.Category;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(long userId);
    Optional<Category> findCategoryById(Long categoryId);
}
