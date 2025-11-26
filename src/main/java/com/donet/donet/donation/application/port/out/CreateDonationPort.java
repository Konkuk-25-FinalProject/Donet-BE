package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Donation;

public interface CreateDonationPort {
    Long createDonation(Donation donation);
}
