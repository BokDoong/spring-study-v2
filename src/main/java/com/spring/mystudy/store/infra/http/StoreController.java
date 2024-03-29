package com.spring.mystudy.store.infra.http;

import com.spring.mystudy.config.auth.JwtUserDetails;
import com.spring.mystudy.store.appication.StoreCommandService;
import com.spring.mystudy.store.infra.http.dto.StoreDtoMapper;
import com.spring.mystudy.store.infra.http.dto.request.ReviewCreateDto;
import com.spring.mystudy.store.infra.http.dto.request.StoreRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final StoreDtoMapper storeDtoMapper;

    @PostMapping()
    public String register(@RequestBody @Valid StoreRegisterDto storeRegisterDto) {
        storeCommandService.create(storeDtoMapper.toCommand(storeRegisterDto));
        return "성공";
    }

    @PostMapping("/{storeId}/reviews")
    public ReviewCreateDto review(@PathVariable("storeId") long storeId, @RequestBody @Valid ReviewCreateDto reviewCreateDto
            , @AuthenticationPrincipal JwtUserDetails userDetails) {
        storeCommandService.review(userDetails.getUserId(), storeId, storeDtoMapper.toCommand(reviewCreateDto));
        return reviewCreateDto;
    }
}
