package com.donet.donet.review.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationReviewRepository extends JpaRepository<DonationReviewJpaEntity, Long> {
    @Query(
            value = "SELECT * FROM donation_review dr ORDER BY dr.created_at DESC LIMIT :limit",
            nativeQuery = true
    )
    List<DonationReviewJpaEntity> findRecentReviews(@Param("limit") Integer limit);
}
