package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DonationReviewEntityMapper {
    public static DonationReviewJpaEntity mapToJpaEntity(DonationReview entity, UserJpaEntity userJpaEntity, DonationJpaEntity donationJpaEntity) {
        return new DonationReviewJpaEntity(entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getTags().stream().collect(Collectors.joining("#")),
                entity.getContent(),
                entity.getImageUrl(),
                userJpaEntity,
                donationJpaEntity);
    }

    public static DonationReview mapToDomainEntity(DonationReviewJpaEntity jpaEntity, UserJpaEntity user, DonationJpaEntity donation) {
        return new DonationReview(jpaEntity.getId(),
                jpaEntity.getTitle(),
                user.getId(),
                user.getNickname(),
                jpaEntity.getSummary(),
                Arrays.stream(jpaEntity.getTags().split("#")).toList(),
                jpaEntity.getImageUrl(),
                jpaEntity.getContent(),
                donation.getId());
    }
}
