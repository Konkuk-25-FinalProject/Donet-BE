package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.GetMainPageInfoUsecase;
import com.donet.donet.donation.application.port.in.dto.response.GetMainPageInfoResponse;
import com.donet.donet.donation.application.port.out.FindCategoriesPort;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMainPageInfoService implements GetMainPageInfoUsecase {
    private final FindDonationPort findDonationPort;
    private final FindCategoriesPort findCategoriesPort;
    private final LoadDonationReviewPort loadDonationReviewPort;

    @Override
    public GetMainPageInfoResponse getMainPageInfo(Long userId) {
        List<Donation> topDonations = findDonationPort.findPopularDonations(3);
        List<Category> donatedCategories = (userId == null) ? List.of() : findCategoriesPort.findDonatedCategories(userId);
        List<Donation> recommendDonations = findDonationPort.findRecommendedDonations(donatedCategories);
        List<DonationReview> recentReviews = loadDonationReviewPort.loadRecentReviews();

        return GetMainPageInfoResponse.from(topDonations, recommendDonations, recentReviews);
    }
}
