package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DonationReviewPersistenceAdapter implements SaveDonationReviewPort, LoadDonationReviewPort {
    private final DonationReviewRepository donationReviewRepository;
    @Override
    public DonationReview save(DonationReview donationReview) {
        DonationReviewJpaEntity saved = donationReviewRepository.save(DonationReviewEntityMapper.mapToJpaEntity(donationReview));
        return DonationReviewEntityMapper.mapToDomainEntity(saved);
    }

    @Override
    public List<DonationReview> loadRecentReviews(Integer limit) {
        return donationReviewRepository.findRecentReviews(limit)
                .stream()
                .map(DonationReviewEntityMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<DonationReview> load(Long donationReviewId) {
        Optional<DonationReviewJpaEntity> found = donationReviewRepository.findById(donationReviewId);
        return found.map(DonationReviewEntityMapper::mapToDomainEntity);
    }
}
