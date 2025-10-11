package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DonationMapper {
    private final DonationImageRepository donationImageRepository;

    public Donation mapToDomainEntity(DonationJpaEntity donationJpaEntity) {
        String imageUrl = donationImageRepository.findByDonationJpaEntity(donationJpaEntity)
                .getImageUrl();

        return new Donation(
                donationJpaEntity.getId(),
                donationJpaEntity.getTitle(),
                donationJpaEntity.getDescription(),
                donationJpaEntity.isAnonymous(),
                donationJpaEntity.getStartDate(),
                donationJpaEntity.getEndDate(),
                donationJpaEntity.getTargetAmount(),
                donationJpaEntity.getCurrentAmount(),
                donationJpaEntity.getViews(),
                imageUrl,
                donationJpaEntity.getUserJpaEntity().getId(),
                donationJpaEntity.getPartnerJpaEntity().getId()
        );
    }
}
