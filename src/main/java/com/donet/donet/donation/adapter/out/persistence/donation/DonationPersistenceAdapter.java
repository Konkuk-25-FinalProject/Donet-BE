package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.UpdateDonationPort;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DonationPersistenceAdapter implements FindDonationPort, UpdateDonationPort {
    private final DonationRepository donationRepository;
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
    public Donation findPopularDonation() {
        return donationMapper.mapToDomainEntity(donationRepository.findPopularDonation());
    }

    @Override
    public Donation findRecommandedDonation(List<Category> categories) {
        List<Long> categoryIds = categories.stream()
                .map(Category::getId)
                .toList();

        DonationJpaEntity donationJpaEntity = donationRepository.findDonationWithAllCategories(categoryIds, categoryIds.size())
                .orElse(donationRepository.findAnyDonation());

        return donationMapper.mapToDomainEntity(donationJpaEntity);
    }

    @Override
    public Donation findDonationById(long id) {
        return null;
    }

    @Override
    public void increaseDonationView(Long donationId) {

    }
}
