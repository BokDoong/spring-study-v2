package com.spring.mystudy;

import com.spring.mystudy.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JavaTest {

    private boolean testValue = false;

    @Test
    void 리스트_테스트() {
        List<String> strings = new ArrayList<>();
        System.out.println(strings.isEmpty());
        System.out.println(!strings.isEmpty());
    }

    @Test
    void 빌더_테스트() {
        User user = User.builder()
                .name("이진")
                .email("ctce7226@gmail.com")
                .genderId(2)
                .imgUrl("~~~~").build();

        System.out.println(user.getNickname());
        System.out.println(user.getEmail());
        System.out.println(user.getUserImage().getImgUrl());
    }

    @Test
    void 전역변수_테스트() {
        testValue = true;
        System.out.println(testValue);
    }
}
