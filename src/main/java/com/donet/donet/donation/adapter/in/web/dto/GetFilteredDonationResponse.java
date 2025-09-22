package com.donet.donet.donation.adapter.in.web.dto;

import java.util.List;

public record GetFilteredDonationResponse(
        DonationSummary recommendedDonation,
        DonationSummary imminentDonation,
        DonationSummary popularDonation,
        List<DonationListItem> donations
) {
    public record DonationSummary(
            Long donationId,
            String title,
            String imageUrl,
            String description,
            String raised
    ) {}

    public record DonationListItem(
            Long donationId,
            String title,
            String imageUrl,
            String description,
            String amount,
            String raised
    ) {}
}