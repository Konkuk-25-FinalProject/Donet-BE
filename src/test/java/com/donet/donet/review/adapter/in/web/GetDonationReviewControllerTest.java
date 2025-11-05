package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
import com.donet.donet.review.adapter.out.persistence.DonationReviewJpaEntity;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailResponse;
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

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GetDonationReviewControllerTest {
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

    @DisplayName("기부후기 조회에 성공한다")
    @Test
    void shouldReturnDonationReview(){
        String title = "제목";
        List<String> tags = List.of("태그1", "태그2");
        String content = "내용";

        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        testDataFactory.createDonation(user.getId());
        DonationReviewJpaEntity donationReview = testDataFactory.createDonationReview(title, tags, content, user.getId());

        UserJpaEntity anotherUser = testDataFactory.createUser("KAKAO", "kakaoId2");

        String accessToken = jwtUtil.createAccessToken(anotherUser.getId());

        GetDonationReviewDetailResponse response = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("reviews/" + donationReview.getId() + "/detail")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", GetDonationReviewDetailResponse.class);

        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getTags()).containsExactly(tags.get(0), tags.get(1));
        assertThat(response.getContent()).isEqualTo(content);
        assertThat(response.getWriterName()).isEqualTo(user.getNickname());
    }
}