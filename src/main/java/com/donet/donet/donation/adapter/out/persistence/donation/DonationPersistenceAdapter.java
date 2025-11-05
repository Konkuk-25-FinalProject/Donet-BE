package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.adapter.out.persistence.category.CategoriesRepository;
import com.donet.donet.donation.adapter.out.persistence.category.CategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationItem.DonationItemJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerRepository;
import com.donet.donet.donation.application.port.out.CreateDonationPort;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.UpdateDonationPort;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.exception.DonationException;
import com.donet.donet.global.exception.UserException;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.*;

@Component
@RequiredArgsConstructor
public class DonationPersistenceAdapter implements FindDonationPort, UpdateDonationPort, CreateDonationPort {
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final PartnerRepository partnerRepository;
    private final CategoriesRepository categoriesRepository;

    private final DonationMapper donationMapper;

    @Override
    public List<Donation> findFilterDonationPage(List<Category> categories, Pageable pageable) {
        List<Long> categoryIds = categories.stream()
                .map(Category::getId)
                .toList();

        Page<DonationJpaEntity> filteredDonations = donationRepository.findDonationWithCategoriesAndPagination(categoryIds, categoryIds.size(), pageable);

        return filteredDonations.getContent().stream()
                .map(donationMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Donation findImminentDonation() {
        return donationMapper.mapToDomainEntity(donationRepository.findImminentDonation());
    }

    @Override
    public List<Donation> findPopularDonations(Integer size) {
        List<DonationJpaEntity> popularDonations = donationRepository.findPopularDonations(size);
        return popularDonations.stream()
                .map(donationMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public List<Donation> findRecommendedDonations(List<Category> categories, Integer donationSize) {
        if(categories.isEmpty()) {
            return donationRepository.findDonationsLimit(donationSize)
                    .stream()
                    .map(donationMapper::mapToDomainEntity)
                    .toList();
        }

        List<Long> categoryIds = categories.stream()
                .map(Category::getId)
                .toList();

        return donationRepository.findDonationWithAllCategories(categoryIds, categoryIds.size(), donationSize)
                .stream()
                .map(donationMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Donation findDonationById(long id) {
        DonationJpaEntity donationJpaEntity = donationRepository.findDonationById(id)
                .orElseThrow(() -> new DonationException(NO_MATCH_DONATION));

        return donationMapper.mapToDomainEntity(donationJpaEntity);
    }

    @Override
    public Donation increaseDonationView(Long donationId) {
        DonationJpaEntity donationJpaEntity = donationRepository.findDonationById(donationId)
                .orElseThrow(() -> new DonationException(NO_MATCH_DONATION));

        donationJpaEntity.increaseView();
        return donationMapper.mapToDomainEntity(donationJpaEntity);
    }

    @Override
    public void createDonation(Donation donation) {
        UserJpaEntity userJpaEntity = userRepository.findById(donation.getUserId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        PartnerJpaEntity partnerJpaEntity = partnerRepository.findById(donation.getPartnerId())
                .orElseThrow(() -> new DonationException(NO_MATCH_PARTNER));

        DonationJpaEntity donationJpaEntity = donationMapper.mapToJpaEntity(donation, userJpaEntity, partnerJpaEntity);

        //카테고리 연결
        List<String> categoryNames = donation.getCategories().stream().map(Category::getName).toList();
        List<CategoryJpaEntity> categoryJpaEntities = categoriesRepository.findAll().stream()
                .filter(cat -> categoryNames.contains(cat.getName()))
                .toList();

        for (CategoryJpaEntity category : categoryJpaEntities) {
            DonationCategoryJpaEntity dc = DonationCategoryJpaEntity.builder()
                    .donationJpaEntity(donationJpaEntity)
                    .categoryJpaEntity(category)
                    .build();
            donationJpaEntity.addDonationCategory(dc);
        }

        //기부 아이템 연결
        donation.getDonationItems()
                .forEach(item -> {
                    DonationItemJpaEntity entity = DonationItemJpaEntity.createNewEntity(item, donationJpaEntity);
                    donationJpaEntity.addDonationItem(entity);
                });

        //이미지 연결
        donation.getImageUrl()
                .forEach(image -> {
                    DonationImageJpaEntity entity = new DonationImageJpaEntity(null, image, donationJpaEntity);
                    donationJpaEntity.addDonationImage(entity);
                });

        DonationJpaEntity savedDonation = donationRepository.save(donationJpaEntity);
    }
}
