package com.donet.donet.donation.adapter.out.persistence.donation;

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
    public boolean createDonation(Donation donation) {
        UserJpaEntity userJpaEntity = userRepository.findById(donation.getUserId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        PartnerJpaEntity partnerJpaEntity = partnerRepository.findById(donation.getPartnerId())
                .orElseThrow(() -> new DonationException(NO_MATCH_PARTNER));

        DonationJpaEntity donationJpaEntity;
        try{
            donationJpaEntity = donationMapper.mapToJpaEntity(donation, userJpaEntity, partnerJpaEntity);
        }catch(Exception e){
            return false;
        }
        donationRepository.save(donationJpaEntity);
        return true;
    }
}
