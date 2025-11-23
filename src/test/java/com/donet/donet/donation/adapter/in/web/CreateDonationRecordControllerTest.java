package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.adapter.in.web.dto.CreateDonationRecordRequest;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationRecord.DonationRecordJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationRecord.DonationRecordRepository;
import com.donet.donet.global.jwt.JwtUtil;
import com.donet.donet.global.util.DatabaseClearExtension;
import com.donet.donet.global.util.TestDataFactory;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.EXPIRED_DONATION;
import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.SUCCESS;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateDonationRecordControllerTest {
    @Autowired
    TestDataFactory testDataFactory;

    @Autowired
    DonationRecordRepository donationRecordRepository;

    @Autowired
    JwtUtil jwtUtil;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("기부기록을 저장해야 한다")
    @Test
    void shouldSaveDonationRecord() {
        // given
        UserJpaEntity donee = testDataFactory.createUser("KAKAO", "kakaoId1");
        DonationJpaEntity donation = testDataFactory.createDonation(donee.getId(), 2000L, LocalDate.now(), LocalDate.now().plusDays(3));

        UserJpaEntity donor = testDataFactory.createUser("KAKAO", "kakaoId2");
        String accessToken = jwtUtil.createAccessToken(donor.getId());

        Long amount = 1000L;
        String walletAddress = "asdf-asdf-asdf-asdf";
        CreateDonationRecordRequest request = new CreateDonationRecordRequest(donation.getId(), amount, true, walletAddress);

        // when
        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/donation/donate")
                .then();

        // then
        validatableResponse.log().all()
                .statusCode(SUCCESS.getStatus())
                .body("code", equalTo(SUCCESS.getCode()));

        Optional<DonationRecordJpaEntity> donationRecord = donationRecordRepository.findById(1L);
        assertThat(donationRecord).isPresent();
        assertThat(donationRecord.get().getWalletAddress()).isEqualTo(walletAddress);
        assertThat(donationRecord.get().getDonationJpaEntity().getId()).isEqualTo(donation.getId());
        assertThat(donationRecord.get().getUserJpaEntity().getId()).isEqualTo(donor.getId());
    }

    @DisplayName("기부가 마감되면 400에러를 반환한다")
    @Test
    void shouldReturn400Code_WhenDonationEnds() {
        // given
        UserJpaEntity donee = testDataFactory.createUser("KAKAO", "kakaoId1");
        DonationJpaEntity donation = testDataFactory.createDonation(donee.getId(), 2000L, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1));

        UserJpaEntity donor = testDataFactory.createUser("KAKAO", "kakaoId2");
        String accessToken = jwtUtil.createAccessToken(donor.getId());

        Long amount = 1000L;
        String walletAddress = "asdf-asdf-asdf-asdf";
        CreateDonationRecordRequest request = new CreateDonationRecordRequest(donation.getId(), amount, true, walletAddress);

        // when
        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/donation/donate")
                .then();

        // then
        validatableResponse.log().all()
                .statusCode(EXPIRED_DONATION.getStatus())
                .body("code", equalTo(EXPIRED_DONATION.getCode()));
    }

    @DisplayName("기부금액이 남아 있는 목표 금액을 넘어서면 400에러를 반환한다")
    @Test
    void shouldReturn400Code_WhenDonationAmountExceedsTargetAmount() {
        // given
        Long targetAmount = 2000L;
        Long donationAmount = 3000L;

        UserJpaEntity donee = testDataFactory.createUser("KAKAO", "kakaoId1");
        DonationJpaEntity donation = testDataFactory.createDonation(donee.getId(), targetAmount, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1));

        UserJpaEntity donor = testDataFactory.createUser("KAKAO", "kakaoId2");
        String accessToken = jwtUtil.createAccessToken(donor.getId());

        String walletAddress = "asdf-asdf-asdf-asdf";
        CreateDonationRecordRequest request = new CreateDonationRecordRequest(donation.getId(), donationAmount, true, walletAddress);

        // when
        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/donation/donate")
                .then();

        // then
        validatableResponse.log().all()
                .statusCode(EXPIRED_DONATION.getStatus())
                .body("code", equalTo(EXPIRED_DONATION.getCode()));
    }
}