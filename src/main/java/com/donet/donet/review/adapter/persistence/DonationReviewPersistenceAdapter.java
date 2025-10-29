package com.donet.donet.review.adapter.persistence;

import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DonationReviewPersistenceAdapter implements SaveDonationReviewPort {
    private final DonationReviewRepository donationReviewRepository;
    @Override
    public DonationReview save(DonationReview donationReview) {
        DonationReviewJpaEntity saved = donationReviewRepository.save(DonationReviewEntityMapper.mapToJpaEntity(donationReview));
        return DonationReviewEntityMapper.mapToDomainEntity(saved);
    }
}
