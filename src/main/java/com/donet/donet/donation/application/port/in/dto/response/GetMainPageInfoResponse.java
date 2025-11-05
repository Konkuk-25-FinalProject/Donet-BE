package com.donet.donet.donation.application.port.in.dto.response;

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
    ){}

    public record RecommendDonation(
            Long donationId,
            String imgUrl,
            String title,
            Long raised
    ){}

    public record DonationReview(
            Long reviewId,
            String title,
            String content,
            String imgUrl
    ){}
}
