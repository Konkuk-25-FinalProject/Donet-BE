package com.donet.donet.donation.application;

import com.donet.donet.donation.adapter.in.web.dto.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.GetFilteredDonationUsecase;
import com.donet.donet.donation.application.port.in.command.GetFilteredDonationCommand;
import com.donet.donet.donation.application.port.out.*;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFilteredDonationService implements GetFilteredDonationUsecase {
    private final FindDonationPort findDonationPort;
    private final GetInterestedCategoriesPort getInterestedCategoriesPort;

    @Override
    public GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command) {
        List<Category> interestedCategories = getInterestedCategoriesPort.findInterestedCategories(command.userId());
        Donation recommendedDonation = findDonationPort.findRecommandedDonation(interestedCategories);
        Donation imminentDonation = findDonationPort.findImminentDonation();
        Donation popularDonation = findDonationPort.findPopularDonation();
        List<Donation> filteredDonations = findDonationPort.findFilterDonationPage(command.categories(), command.pageable());

        return GetFilteredDonationResponse.of(recommendedDonation, imminentDonation, popularDonation, filteredDonations);
    }
}
