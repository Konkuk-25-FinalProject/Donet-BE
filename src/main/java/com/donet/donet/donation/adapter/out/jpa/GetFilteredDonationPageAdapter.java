package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.GetFilteredDonationPagePort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFilteredDonationPageAdapter implements GetFilteredDonationPagePort {
    @Override
    public List<Donation> getFilterDonationPage(List<Category> categories, Pageable pageable) {
        return List.of();
    }
}
