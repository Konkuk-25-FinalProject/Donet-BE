package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerJpaEntity;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.DonationItem;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DonationMapper {

    public Donation mapToDomainEntity(DonationJpaEntity donationJpaEntity) {
        List<String> imageUrl = donationJpaEntity.getDonationImageJpaEntities()
                .stream()
                .map(DonationImageJpaEntity::getImageUrl)
                .toList();

        List<DonationItem> donationItems = donationJpaEntity.getDonationItemJpaEntities()
                .stream()
                .map(entity -> new DonationItem(entity.getId(), entity.getName(), entity.getQuantity(), entity.getPrice()))
                .toList();

        List<Category> categories = donationJpaEntity.getDonationCategories()
                .stream()
                .map(DonationCategoryJpaEntity::getCategoryJpaEntity)
                .map(entity -> new Category(entity.getId(), entity.getName()))
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
                donationItems,
                categories,
                donationJpaEntity.getStatus().name()
        );
    }

    public DonationJpaEntity mapToJpaEntity(
            Donation donation,
            UserJpaEntity userJpaEntity,
            PartnerJpaEntity partnerJpaEntity
    ) {
        return DonationJpaEntity.builder()
                .title(donation.getTitle())
                .anonymous(donation.getAnonymous())
                .startDate(donation.getStartDate())
                .endDate(donation.getEndDate())
                .description(donation.getDescription())
                .targetAmount(donation.getTargetAmount())
                .currentAmount(donation.getCurrentAmount())
                .views(donation.getViews())
                .userJpaEntity(userJpaEntity)
                .partnerJpaEntity(partnerJpaEntity)
                .build();
    }
}
