package com.donet.donet.review.adapter.persistence;

import com.donet.donet.review.domain.DonationReview;

public class DonationReviewEntityMapper {
    public static DonationReviewJpaEntity mapToJpaEntity(DonationReview entity) {
        return new DonationReviewJpaEntity(entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getContent(),
                entity.getImageUrl());
    }

    public static DonationReview mapToDomainEntity(DonationReviewJpaEntity jpaEntity) {
        return new DonationReview(jpaEntity.getId(),
                jpaEntity.getTitle(),
                jpaEntity.getSummary(),
                jpaEntity.getImageUrl(),
                jpaEntity.getContent());
    }
}
