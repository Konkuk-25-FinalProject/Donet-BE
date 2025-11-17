package com.donet.donet.review.adapter.in.web;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
import com.donet.donet.review.adapter.in.web.dto.CreateDonationReviewRequest;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateDonationReviewControllerTest {
    @Autowired
    TestDataFactory testDataFactory;

    @MockitoBean
    ImageUploaderPort imageUploaderPort;

    @Autowired
    JwtUtil jwtUtil;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("이미지를 포함한 요청은 성공한다")
    @Test
    void shouldSucceedWhenRequestWithImage(){

        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        testDataFactory.createDonation(user.getId());
        String accessToken = jwtUtil.createAccessToken(user.getId());

        CreateDonationReviewRequest reviewRequest = new CreateDonationReviewRequest(1L, "제목", "요약", List.of("태그"), "내용");
        byte[] fakeImage = "fake image data".getBytes(StandardCharsets.UTF_8);

        BDDMockito.given(imageUploaderPort.upload(Mockito.any())).willReturn("image");

        given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.MULTIPART)
                .accept(ContentType.JSON)
                .multiPart("reviewRequest", reviewRequest, "application/json")
                .multiPart("reviewImage", "image.png", fakeImage, "image/png")
            .when()
                .post("/reviews")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(1000))
                .body("data", is(nullValue()));
    }

    @DisplayName("이미지가 없는 요청도 성공한다")
    @Test
    void shouldSucceedWhenRequestWithoutImage(){
        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        testDataFactory.createDonation(user.getId());
        String accessToken = jwtUtil.createAccessToken(user.getId());

        CreateDonationReviewRequest reviewRequest = new CreateDonationReviewRequest(1L, "제목", "요약", List.of("태그"),"내용");

        given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.MULTIPART)
                .accept(ContentType.JSON)
                .multiPart("reviewRequest", reviewRequest, "application/json")
                .when()
                .post("/reviews")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(1000))
                .body("data", is(nullValue()));
    }
}