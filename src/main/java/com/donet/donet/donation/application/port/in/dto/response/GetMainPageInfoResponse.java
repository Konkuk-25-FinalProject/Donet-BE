package com.donet.donet.donation.application.port.in.dto.response;

import com.donet.donet.donation.domain.Donation;
import com.donet.donet.review.domain.DonationReview;

import java.util.List;

public record GetMainPageInfoResponse (
        List<TopDonation> topDonations,
        List<RecommendDonation> recommendDonations,
        List<DonationReviewResponse> donationReviews
){
    public record TopDonation (
            Long donationId,
            String imgUrl,
            String title,
            String content,
            Long raised
    ){
        public static TopDonation fromDonation (Donation donation) {
            List<String> imageUrls = donation.getImageUrl();
            String imageUrl = imageUrls.isEmpty() ? null : imageUrls.get(0);
            return new TopDonation(
                    donation.getId(), imageUrl, donation.getTitle(), donation.getDescription(), donation.getCurrentAmount()
            );
        }
    }

    public record RecommendDonation(
            Long donationId,
            String imgUrl,
            String title,
            Long raised
    ){
        public static RecommendDonation fromDonation (Donation donation) {
            String imgUrl = donation.getImageUrl().isEmpty() ? null : donation.getImageUrl().get(0);
            return new RecommendDonation(
                    donation.getId(), imgUrl, donation.getTitle(), donation.getCurrentAmount()
            );
        }
    }

    public record DonationReviewResponse(
            Long reviewId,
            String title,
            String content,
            String imgUrl
    ){
        public static DonationReviewResponse fromDonationReview (DonationReview review) {
            String imageUrl = review.getImageUrl() != null ? review.getImageUrl() : "";
            return new DonationReviewResponse(
                    review.getId(), review.getTitle(), review.getContent(), imageUrl
            );
        }
    }

    public static GetMainPageInfoResponse from(List<Donation> topDonations, List<Donation> recommendDonations, List<DonationReview> donationReviews) {
        List<TopDonation> topDonationsResponse = topDonations.stream()
                .map(TopDonation::fromDonation)
                .toList();

        List<RecommendDonation> recommendDonationsResponse = recommendDonations.stream()
                .map(RecommendDonation::fromDonation)
                .toList();

        List<DonationReviewResponse> donationReviewsResponse = donationReviews.stream()
                .map(DonationReviewResponse::fromDonationReview)
                .toList();

        return new GetMainPageInfoResponse(topDonationsResponse, recommendDonationsResponse, donationReviewsResponse);
    }
}
