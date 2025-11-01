package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DonationMapper {
    //TODO: 의존성 없애기 --> Adapter에서 조회해서 인자로 넘겨주는 방식으로 변경할 것
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
