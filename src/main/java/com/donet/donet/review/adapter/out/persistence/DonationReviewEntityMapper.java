package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.review.domain.DonationReview;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DonationReviewEntityMapper {
    public static DonationReviewJpaEntity mapToJpaEntity(DonationReview entity) {
        return new DonationReviewJpaEntity(entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getTags().stream().collect(Collectors.joining("#")),
                entity.getContent(),
                entity.getImageUrl(),
                null);
    }

    public static DonationReview mapToDomainEntity(DonationReviewJpaEntity jpaEntity) {
        return new DonationReview(jpaEntity.getId(),
                jpaEntity.getTitle(),
                jpaEntity.getWriter().getNickname(),
                jpaEntity.getSummary(),
                Arrays.stream(jpaEntity.getTags().split("#")).toList(),
                jpaEntity.getImageUrl(),
                jpaEntity.getContent());
    }
}
