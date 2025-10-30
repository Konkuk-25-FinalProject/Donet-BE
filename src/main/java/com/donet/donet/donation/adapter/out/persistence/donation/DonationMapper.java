package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DonationMapper {
    private final DonationImageRepository donationImageRepository;

    public Donation mapToDomainEntity(DonationJpaEntity donationJpaEntity) {
        List<String> imageUrl = donationImageRepository.findByDonationJpaEntity(donationJpaEntity)
                .stream()
                .map(DonationImageJpaEntity::getImageUrl)
                .toList();

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
                donationJpaEntity.getPartnerJpaEntity().getId(),
                null
        );
    }
}
