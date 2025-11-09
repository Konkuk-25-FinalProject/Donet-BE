package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Donation;

public interface UpdateDonationPort {
    Donation increaseDonationView(Long donationId);
    Donation addDonationAmount(Long donationId, Long amount);
}
