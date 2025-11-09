package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.adapter.out.persistence.UserEntityMapper;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DonationReviewPersistenceAdapter implements SaveDonationReviewPort, LoadDonationReviewPort {
    private final DonationReviewRepository donationReviewRepository;
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public DonationReview save(DonationReview donationReview) {
        UserJpaEntity userJpaEntity = userRepository.findById(donationReview.getWriter().getId()).get();
        DonationReviewJpaEntity saved = donationReviewRepository.save(DonationReviewEntityMapper.mapToJpaEntity(donationReview, userJpaEntity));
        return DonationReviewEntityMapper.mapToDomainEntity(saved, userEntityMapper.mapToDomainEntity(userJpaEntity));
    }

    @Override
    public List<DonationReview> loadRecentReviews(Integer limit) {
        return donationReviewRepository.findRecentReviews(limit)
                .stream()
                .map(jpaEntity -> DonationReviewEntityMapper.mapToDomainEntity(jpaEntity, userEntityMapper.mapToDomainEntity(jpaEntity.getWriter())))
                .toList();
    }

    @Override
    public Optional<DonationReview> load(Long donationReviewId) {
        Optional<DonationReviewJpaEntity> found = donationReviewRepository.findById(donationReviewId);
        return found.map(entity -> DonationReviewEntityMapper.mapToDomainEntity(entity,
                userEntityMapper.mapToDomainEntity(entity.getWriter())));
    }
}
