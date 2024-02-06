package com.spring.mystudy.user.domain.info;

import com.spring.mystudy.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<UserPrefer> userPrefers = new ArrayList<>();
    @OneToMany(mappedBy = "category")
    private List<Store> stores = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
        this.userPrefers = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public void addUserPrefer(UserPrefer userPrefer) {
        userPrefers.add(userPrefer);
    }

    public void addStore(Store store) {
        stores.add(store);
    }
}
