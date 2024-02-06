package com.spring.mystudy.mission.application;

import com.spring.mystudy.exception.NotFoundException;
import com.spring.mystudy.exception.code.ErrorCode;
import com.spring.mystudy.mission.application.dto.MissionCommandMapper;
import com.spring.mystudy.mission.application.dto.request.MissionCreateCommand;
import com.spring.mystudy.mission.domain.Mission;
import com.spring.mystudy.store.domain.Store;
import com.spring.mystudy.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionCommandMapper missionCommandMapper;
    private final StoreRepository storeRepository;

    @Transactional
    public long register(long storeId, MissionCreateCommand missionCreateCommand) {
        // 1. 가게 찾기
        Store store = findStore(storeId);
        // 2. Mission 생성
        registerAndSaveMission(store, missionCreateCommand);
        return 0L;
    }

    private long registerAndSaveMission(Store store, MissionCreateCommand missionCreateCommand) {
        Mission mission = registerMission(store, missionCreateCommand);
        // ㅁㅇㅁㄴㅇㅁㅇㄴㅁ
        return 0L;
    }

    private Mission registerMission(Store store, MissionCreateCommand missionCreateCommand) {
        return missionCommandMapper.toEntity(store, missionCreateCommand);
    }

    private Store findStore(long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.STORE_NOT_FOUND, storeId));
    }
}
