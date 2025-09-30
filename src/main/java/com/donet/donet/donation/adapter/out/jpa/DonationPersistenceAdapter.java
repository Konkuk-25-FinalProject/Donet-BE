package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DonationPersistenceAdapter implements FindDonationPort {
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
