package com.donet.donet.donation.application.port.in.dto.response;

import com.donet.donet.donation.domain.Donation;

import java.util.List;

public record GetMainPageInfoResponse (
        List<TopDonation> topDonations,
        List<RecommendDonation> recommendDonations,
        List<DonationReview> donationReviews
){
    public record TopDonation (
            Long donationId,
            String imgUrl,
            String title,
            String content,
            Long raised
    ){
        public static TopDonation fromDonation (Donation donation) {
            return new TopDonation(
                    donation.getId(), donation.getImageUrl().get(0), donation.getTitle(), donation.getDescription(), donation.getCurrentAmount()
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
            return new RecommendDonation(
                    donation.getId(), donation.getImageUrl().get(0), donation.getTitle(), donation.getCurrentAmount()
            );
        }
    }

    public record DonationReview(
            Long reviewId,
            String title,
            String content,
            String imgUrl
    ){
        public static GetMainPageInfoResponse.DonationReview fromDonationReview (DonationReview review) {
            return new GetMainPageInfoResponse.DonationReview(
                    review.reviewId, review.title, review.content, review.imgUrl
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

        List<GetMainPageInfoResponse.DonationReview> donationReviewsResponse = donationReviews.stream()
                .map(GetMainPageInfoResponse.DonationReview::fromDonationReview)
                .toList();

        return new GetMainPageInfoResponse(topDonationsResponse, recommendDonationsResponse, donationReviewsResponse);
    }
}
