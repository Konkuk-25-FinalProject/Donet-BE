package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.domain.User;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DonationReviewEntityMapper {
    public static DonationReviewJpaEntity mapToJpaEntity(DonationReview entity, UserJpaEntity userJpaEntity) {
        return new DonationReviewJpaEntity(entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getTags().stream().collect(Collectors.joining("#")),
                entity.getContent(),
                entity.getImageUrl(),
                userJpaEntity);
    }

    public static DonationReview mapToDomainEntity(DonationReviewJpaEntity jpaEntity, User user) {
        return new DonationReview(jpaEntity.getId(),
                jpaEntity.getTitle(),
                user,
                jpaEntity.getSummary(),
                Arrays.stream(jpaEntity.getTags().split("#")).toList(),
                jpaEntity.getImageUrl(),
                jpaEntity.getContent());
    }
}
