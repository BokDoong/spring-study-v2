package com.spring.mystudy.store.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Embeddable
public class Images {

    @OneToMany(mappedBy = "store")
    private final List<StoreImage> storeImages;

    protected Images() {
        this.storeImages = new LinkedList<>();
    }

    List<StoreImage> getImages() {
        return Collections.unmodifiableList(storeImages);
    }
}
