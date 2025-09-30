package com.donet.donet.donation.adapter.out.persistence;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DonationPersistenceAdapter implements FindDonationPort {
    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;

    @Override
    public List<Donation> findFilterDonationPage(List<Category> categories, Pageable pageable) {
        return List.of();
    }

    @Override
    public Donation findImminentDonation() {
        return null;
    }

    @Override
    public Donation findPopularDonation() {
        return null;
    }

    @Override
    public Donation findRecommandedDonation(List<Category> categories) {
        return null;
    }
}
