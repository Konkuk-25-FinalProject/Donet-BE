package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.user.domain.JoinedDonation;
import com.donet.donet.user.domain.RegisteredDonation;
import com.donet.donet.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindDonationPort {
    List<Donation> findFilterDonationPage(List<Category> categories, Pageable pageable);
    Donation findImminentDonation();
    List<Donation> findPopularDonations(Integer size);
    List<Donation> findRecommendedDonations(List<Category> categories, Integer donationSize);
    Donation findDonationById(long id);

    List<JoinedDonation> findJoinedDonations(User user, int size);
    List<RegisteredDonation> findRegistertedDonations(User user, int size);
}
