package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.GetPopularDonationPort;
import com.donet.donet.donation.domain.Donation;
import org.springframework.stereotype.Component;

@Component
public class GetPopularDonationAdapter implements GetPopularDonationPort {
    @Override
    public Donation getPopularDonation() {
        return null;
    }
}
