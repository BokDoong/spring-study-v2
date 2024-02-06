package com.spring.mystudy.user.infra;

import com.spring.mystudy.user.domain.User;
import com.spring.mystudy.user.domain.UserRepository;
import com.spring.mystudy.user.domain.info.Category;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJpaRepository implements UserRepository {

    private final EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public Optional<User> findById(long userId) {
        return Optional.ofNullable(em.find(User.class, userId));
    }

    @Override
    public Optional<Category> findCategoryById(Long categoryId) {
        return Optional.ofNullable(em.find(Category.class, categoryId));
    }
}
