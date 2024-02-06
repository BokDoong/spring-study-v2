package com.spring.mystudy.mission.domain;

import com.spring.mystudy.common.basetime.BaseTimeEntity;
import com.spring.mystudy.store.domain.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Embedded
    private Compensation compensation;
    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @Builder
    public Mission(Store store, String content, int price, LocalDateTime deadline) {
        this.store = store;
        this.content = content;
        this.deadline = deadline;
        compensation = new Compensation(price);
    }
}
