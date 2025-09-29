package com.donet.donet.donation.application;

import com.donet.donet.donation.adapter.in.web.dto.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.GetFilteredDonationUsecase;
import com.donet.donet.donation.application.port.in.command.GetFilteredDonationCommand;
import com.donet.donet.donation.application.port.out.*;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFilteredDonationService implements GetFilteredDonationUsecase {
    private final GetInterestedCategoriesPort getInterestedCategoriesPort;
    private final GetRecommandedDonationPort getRecommandedDonationPort;
    private final GetImminentDonationPort getImminentDonationPort;
    private final GetPopularDonationPort getPopularDonationPort;
    private final GetFilteredDonationPagePort getFilteredDonationPagePort;

    @Override
    public GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command) {
        List<Category> interestedCategories = getInterestedCategoriesPort.getInterestedCategories(command.userId());
        Donation recommendedDonation = getRecommandedDonationPort.getRecommandedDonation(interestedCategories);
        Donation imminentDonation = getImminentDonationPort.getImminentDonation();
        Donation popularDonation = getPopularDonationPort.getPopularDonation();
        List<Donation> filteredDonations = getFilteredDonationPagePort.getFilterDonationPage(command.categories(), command.pageable());

        return GetFilteredDonationResponse.of(recommendedDonation, imminentDonation, popularDonation, filteredDonations);
    }
}
