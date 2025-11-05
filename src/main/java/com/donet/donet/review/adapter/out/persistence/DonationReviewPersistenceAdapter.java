package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationRepository;
import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DonationReviewPersistenceAdapter implements SaveDonationReviewPort, LoadDonationReviewPort {
    private final DonationReviewRepository donationReviewRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @Override
    public DonationReview save(DonationReview donationReview) {
        UserJpaEntity userJpaEntity = userRepository.findById(donationReview.getWriterId()).get();
        DonationJpaEntity donationJpaEntity = donationRepository.findById(donationReview.getDonationId()).get();
        DonationReviewJpaEntity saved = donationReviewRepository.save(DonationReviewEntityMapper.mapToJpaEntity(donationReview, userJpaEntity, donationJpaEntity));
        return DonationReviewEntityMapper.mapToDomainEntity(saved, userJpaEntity, donationJpaEntity);
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
                entity.getWriter(),
                entity.getDonation()));
    }

    @Override
    public List<DonationReview> findPage(int size, Long lastId) {
        List<DonationReviewJpaEntity> jpaEntities = donationReviewRepository.findAllByIdLessThanOrderByIdDesc(lastId, PageRequest.of(0, size));
        return jpaEntities.stream()
                .map(entity -> DonationReviewEntityMapper.mapToDomainEntity(entity,
                        entity.getWriter(),
                        entity.getDonation()))
                .toList();
    }

}
