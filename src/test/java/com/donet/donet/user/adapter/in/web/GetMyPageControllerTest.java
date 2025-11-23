package com.donet.donet.user.adapter.in.web;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
import com.donet.donet.user.adapter.in.web.dto.GetMyPageApiResponse;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GetMyPageControllerTest {
    @Autowired
    TestDataFactory testDataFactory;

    @Autowired
    JwtUtil jwtUtil;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("마이페이지 조회에 성공한다")
    @Test
    void shouldReturnMyPage() {
        // given
        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoid1");
        String accessToken = jwtUtil.createAccessToken(user.getId());
        IntStream.rangeClosed(1, 20).forEach(i -> {
            testDataFactory.createDonation(user.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));
        });
        DonationJpaEntity donation = testDataFactory.createDonation(user.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));
        testDataFactory.createDonationReview("제목", List.of("태그1", "태그2"), "내용", user.getId(), donation.getId());

        long donationAmount = 2000L;
        UserJpaEntity another = testDataFactory.createUser("KAKAO", "kakaoid2");
        IntStream.rangeClosed(1, 30).forEach(i -> {
            DonationJpaEntity anothersDonation = testDataFactory.createDonation(another.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));
            testDataFactory.createDonationRecord(user.getId(), anothersDonation.getId(), donationAmount);
        });
        DonationJpaEntity anothersDonation = testDataFactory.createDonation(another.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));
        testDataFactory.createDonationRecord(user.getId(), anothersDonation.getId(), donationAmount);

        GetMyPageApiResponse response = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("users/me")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", GetMyPageApiResponse.class);

        assertThat(response.nickname()).isEqualTo(user.getNickname());
        assertThat(response.profileImageUrl()).isEqualTo(user.getProfileImage());
        assertThat(response.walletAddress()).isEqualTo(user.getWalletAddress());
        assertThat(response.registeredDonations()).hasSize(20);
        assertThat(response.registeredDonations().get(0).reviewable()).isFalse();
        assertThat(response.registeredDonations().get(0).donationId()).isEqualTo(donation.getId());
        assertThat(response.registeredDonations().get(1).reviewable()).isTrue();
        assertThat(response.joinedDonations()).hasSize(20);
        assertThat(response.joinedDonations().get(0).amount()).isEqualTo(donationAmount);
        assertThat(response.joinedDonations().get(0).donationId()).isEqualTo(anothersDonation.getId());
    }

}