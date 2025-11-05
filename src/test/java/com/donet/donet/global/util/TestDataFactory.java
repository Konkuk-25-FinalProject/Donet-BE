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
import com.donet.donet.review.adapter.out.persistence.DonationReviewJpaEntity;
import com.donet.donet.review.adapter.out.persistence.DonationReviewRepository;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    DonationReviewRepository donationReviewRepository;

    public UserJpaEntity createUser(String provider, String loginId) {
        return userRepository.save(new UserJpaEntity(null, "닉네임", "이미지",  provider, loginId, "waller"));
    }

    @Transactional
    public UserJpaEntity createDonation(Long userId) {
        UserJpaEntity user = userRepository.findById(userId).get();
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

    @Transactional
    public DonationReviewJpaEntity createDonationReview(String title, List<String> tags, String content, Long userId){
        UserJpaEntity user = userRepository.findById(userId).get();
        String flattenTags = tags.stream().collect(Collectors.joining("#"));
        DonationReviewJpaEntity donation = new DonationReviewJpaEntity(null, title, "요약", flattenTags, content, "이미지", user);
        return donationReviewRepository.save(donation);
    }
}
