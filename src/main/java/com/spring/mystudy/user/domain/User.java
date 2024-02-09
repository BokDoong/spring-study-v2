package com.spring.mystudy.user.domain;

import com.spring.mystudy.common.basetime.BaseTimeEntity;
import com.spring.mystudy.store.domain.location.Address;
import com.spring.mystudy.store.domain.review.Review;
import com.spring.mystudy.user.domain.info.UserPrefer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.spring.mystudy.config.auth.PasswordEncoderConfig.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private UserGender userGender;
    @Embedded
    private Address address;
    @Embedded
    private UserImage userImage;
    @Column(name="birthdate")
    private LocalDateTime birthDate;
    @Column(name="point")
    private int point;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissions = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPrefer> userPrefers = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(String name, String nickname, String email, String password, String phoneNumber, Integer genderId,
                LocalDateTime birthDate, String firstAddress, String secondAddress, String imgUrl) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password == null ? null : passwordEncoder().encode(password);
        this.phoneNumber = phoneNumber;
        this.userGender = toGender(genderId);
        this.birthDate = birthDate;
        address = new Address(firstAddress, secondAddress);
        userImage = imgUrl.isBlank() ? new UserImage() : new UserImage(imgUrl);
        point = 0;
        role = Role.USER;
        userMissions = new ArrayList<>();
        userPrefers = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum UserGender {
        MALE("남자"),
        FEMALE("여자"),
        NONE("선택안함")
        ;

        private final String gender;
    }

    // TODO: Enum 으로 넘겨받기
    private UserGender toGender(Integer userId) {
        switch (userId) {
            case 0:
                return UserGender.MALE;
            case 1:
                return UserGender.FEMALE;
            case 2:
                return UserGender.NONE;
        }

        return null;
    }

    public void addUserPrefer(UserPrefer userPrefer) {
        userPrefers.add(userPrefer);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }
}
