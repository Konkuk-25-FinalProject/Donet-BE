package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.GetImminentDonationPort;
import com.donet.donet.donation.domain.Donation;
import org.springframework.stereotype.Component;

@Component
public class GetImminentDonationAdapter implements GetImminentDonationPort {
    @Override
    public Donation getImminentDonation() {
        return null;
    }
}
