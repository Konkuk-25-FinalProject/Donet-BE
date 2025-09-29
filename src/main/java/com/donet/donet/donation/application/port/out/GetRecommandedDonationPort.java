package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;

import java.util.List;

public interface GetRecommandedDonationPort {
    Donation getRecommandedDonation(List<Category> categories);
}
