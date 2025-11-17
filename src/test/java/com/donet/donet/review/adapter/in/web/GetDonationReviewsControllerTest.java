package com.donet.donet.review.adapter.in.web;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewsResponse;
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

import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GetDonationReviewsControllerTest {
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

    @DisplayName("기부후기 리스트 조회에 성공한다")
    @Test
    void shouldReturnDonationReviews(){
        int size = 20;
        String title = "제목";
        List<String> tags = List.of("태그1", "태그2");
        String content = "내용";

        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        IntStream.rangeClosed(1, size + 1).forEach(i->{
            DonationJpaEntity donation = testDataFactory.createDonation(user.getId());
            testDataFactory.createDonationReview(title + i, tags, content, user.getId(), donation.getId());
        });

        UserJpaEntity anotherUser = testDataFactory.createUser("KAKAO", "kakaoId2");


        String accessToken = jwtUtil.createAccessToken(anotherUser.getId());

        GetDonationReviewsResponse response = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("size", size)
                .queryParam("lastId", 900)
                .when()
                .get("reviews")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", GetDonationReviewsResponse.class);

        assertThat(response.reviews()).hasSize(size);
        assertThat(response.reviews().get(0).title()).isEqualTo(title + 21);
        assertThat(response.reviews().get(0).writer()).isEqualTo(user.getNickname());
        assertThat(response.hasNext()).isTrue();
        assertThat(response.lastId()).isEqualTo(2L);
    }

    @DisplayName("lastId가 null이어도 기부후기 리스트 조회에 성공한다")
    @Test
    void shouldReturnDonationReviewsWhenLastIdIsNull(){
        int size = 20;
        String title = "제목";
        List<String> tags = List.of("태그1", "태그2");
        String content = "내용";

        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        IntStream.rangeClosed(1, size + 1).forEach(i->{
            DonationJpaEntity donation = testDataFactory.createDonation(user.getId());
            testDataFactory.createDonationReview(title + i, tags, content, user.getId(), donation.getId());
        });

        UserJpaEntity anotherUser = testDataFactory.createUser("KAKAO", "kakaoId2");

        String accessToken = jwtUtil.createAccessToken(anotherUser.getId());

        GetDonationReviewsResponse response = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("size", size)
                .when()
                .get("reviews")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", GetDonationReviewsResponse.class);

        assertThat(response.reviews()).hasSize(size);
        assertThat(response.reviews().get(0).title()).isEqualTo(title + 21);
        assertThat(response.reviews().get(0).writer()).isEqualTo(user.getNickname());
        assertThat(response.hasNext()).isTrue();
        assertThat(response.lastId()).isEqualTo(2L);
    }
}