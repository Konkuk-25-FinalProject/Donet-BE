package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindDonationPort {
    List<Donation> findFilterDonationPage(List<Category> categories, Pageable pageable);
    Donation findImminentDonation();
    Donation findPopularDonation();
    Donation findRecommandedDonation(List<Category> categories);
    Donation findDonationById(long id);
}
