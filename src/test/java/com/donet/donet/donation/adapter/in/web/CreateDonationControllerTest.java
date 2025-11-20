package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.adapter.in.web.dto.CreateDonationRequest;
import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
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

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.TARGET_AMOUNT_LOWER_BOUND_LIMIT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateDonationControllerTest {
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

    @DisplayName("목표 금액이 0이하면 기부 생성에 실패한다")
    @Test
    void shouldReturn400CodeWhenTargetAmountIsLessThan1(){
        UserJpaEntity user = testDataFactory.createUser("KAKAO", "kakaoId1");
        testDataFactory.createDonation(user.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));
        String accessToken = jwtUtil.createAccessToken(user.getId());
        Long targetAmount = 0L;
        CreateDonationRequest donationRequest = getDonationRequest(targetAmount);

        given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.MULTIPART)
                .accept(ContentType.JSON)
                .multiPart("request", donationRequest, "application/json")
                .when()
                .post("/donation/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("code", equalTo(3009))
                .body("message", equalTo(TARGET_AMOUNT_LOWER_BOUND_LIMIT.getMessage()));
    }

    private static CreateDonationRequest getDonationRequest(Long targetAmount) {
        return new CreateDonationRequest("햇반 후원 부탁드려요",
                true,
                List.of(new CreateDonationRequest.Item("햇반", 10L, 3000L)),
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                List.of("식료품"),
                targetAmount,
                1L,
                "햇반 후원부탁드립니다");
    }

}