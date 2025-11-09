package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.dto.response.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.GetFilteredDonationUsecase;
import com.donet.donet.donation.application.port.in.dto.command.GetFilteredDonationCommand;
import com.donet.donet.donation.application.port.out.*;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.global.exception.DonationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.NOT_FOUND_POPULAR_DONATION;
import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.NOT_FOUND_RECOMMEND_DONATION;

@Service
@RequiredArgsConstructor
public class GetFilteredDonationService implements GetFilteredDonationUsecase {
    private final FindDonationPort findDonationPort;
    private final FindCategoriesPort findCategoriesPort;

    @Override
    public GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command) {
        List<Category> interestedCategories = findCategoriesPort.findInterestedCategories(command.userId());
        List<Donation> recommendedDonations = findDonationPort.findRecommendedDonations(interestedCategories, 1);
        if (recommendedDonations.isEmpty()) {
            throw new DonationException(NOT_FOUND_RECOMMEND_DONATION);
        }
        Donation recommendedDonation = recommendedDonations.get(0);

        Donation imminentDonation = findDonationPort.findImminentDonation();

        List<Donation> popularDonations = findDonationPort.findPopularDonations(1);
        if (popularDonations.isEmpty()) {
            throw new DonationException(NOT_FOUND_POPULAR_DONATION);
        }
        Donation popularDonation = popularDonations.get(0);

        List<Category> categories = findCategoriesPort.findCategoriesByName(command.categories());
        List<Donation> filteredDonations = findDonationPort.findFilterDonationPage(categories, command.pageable());

        return GetFilteredDonationResponse.of(recommendedDonation, imminentDonation, popularDonation, filteredDonations);
    }
}
