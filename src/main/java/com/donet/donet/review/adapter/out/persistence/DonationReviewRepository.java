package com.donet.donet.review.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DonationReviewRepository extends JpaRepository<DonationReviewJpaEntity, Long> {
    @Query(
            value = "SELECT * FROM donation_review dr ORDER BY dr.created_at DESC LIMIT :limit",
            nativeQuery = true
    )
    List<DonationReviewJpaEntity> findRecentReviews(@Param("limit") Integer limit);

    List<DonationReviewJpaEntity> findAllByIdLessThanOrderByIdDesc(Long donationReviewId, Pageable pageable);

    @Query("""
        select review.donation.id from DonationReviewJpaEntity review where review.donation.id in :ids
    """)
    List<Long> findAllDonationIdHavingReview(@Param("ids") Set<Long> ids);
}
