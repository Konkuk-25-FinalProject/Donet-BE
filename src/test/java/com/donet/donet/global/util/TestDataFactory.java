package com.donet.donet.global.util;

import com.donet.donet.donation.adapter.out.persistence.PartnerJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.PartnerRepository;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationRepository;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataFactory {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    DonationRepository donationRepository;

    public UserJpaEntity createUser() {
        return userRepository.save(new UserJpaEntity(null, "닉네임", "이미지",  "KAKAO", "kakaocode", "waller"));
    }

    @Transactional
    public UserJpaEntity createUserWhoWroteDonation() {
        UserJpaEntity user = userRepository.save(new UserJpaEntity(null, "닉네임", "이미지", "KAKAO", "kakaocode", "waller"));
        PartnerJpaEntity partner = partnerRepository.save(new PartnerJpaEntity(null, "파트너사", "지갑주소"));
        donationRepository.save(new DonationJpaEntity(null, "제목", true, LocalDate.now(), LocalDate.now(), "설명", 1000L, 1000L, 0L, user, partner));
        return user;
    }
}
