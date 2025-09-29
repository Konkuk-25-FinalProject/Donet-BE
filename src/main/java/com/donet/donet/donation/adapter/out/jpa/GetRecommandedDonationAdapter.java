package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.GetRecommandedDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRecommandedDonationAdapter implements GetRecommandedDonationPort {
    @Override
    public Donation getRecommandedDonation(List<Category> categories) {
        return null;
    }
}
