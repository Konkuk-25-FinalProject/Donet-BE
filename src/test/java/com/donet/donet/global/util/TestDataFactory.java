package com.donet.donet.global.util;

import com.donet.donet.donation.adapter.out.persistence.category.CategoriesRepository;
import com.donet.donet.donation.adapter.out.persistence.category.CategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationRepository;
import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryRepository;
import com.donet.donet.donation.adapter.out.persistence.donationItem.DonationItemJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerRepository;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataFactory {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    DonationCategoryRepository donationCategoryRepository;

    public UserJpaEntity createUser() {
        return userRepository.save(new UserJpaEntity(null, "닉네임", "이미지",  "KAKAO", "kakaocode", "waller"));
    }

    @Transactional
    public UserJpaEntity createUserWhoWroteDonation() {
        UserJpaEntity user = userRepository.save(new UserJpaEntity(null, "닉네임", "이미지", "KAKAO", "kakaocode", "waller"));
        PartnerJpaEntity partner = partnerRepository.save(new PartnerJpaEntity(null, "파트너사", "지갑주소"));
        DonationItemJpaEntity donationItem = new DonationItemJpaEntity(null, "삼다수", 1L, 5000L, null);

        DonationJpaEntity donation = donationRepository.save(new DonationJpaEntity(null, "제목", true,
                LocalDate.now(), LocalDate.now(),
                "설명", 1000L, 1000L, 0L,
                user,
                partner,
                new ArrayList<>(List.of(donationItem)),
                new ArrayList<>(),
                new ArrayList<>(List.of())));

        CategoryJpaEntity category = categoriesRepository.save(new CategoryJpaEntity(null, "생필품", null));
        DonationCategoryJpaEntity donationCategory = new DonationCategoryJpaEntity(null, donation, category);
        donationCategoryRepository.save(donationCategory);

        donationRepository.save(donation);

        return user;
    }
}
