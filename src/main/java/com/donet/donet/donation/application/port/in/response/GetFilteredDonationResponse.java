package com.donet.donet.donation.application.port.in.response;

import com.donet.donet.donation.domain.Donation;

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
    ) {
        public static DonationSummary from(Donation donation) {
            return new DonationSummary(
                    donation.getId(),
                    donation.getTitle(),
                    donation.getImageUrl(),
                    donation.getDescription(),
                    donation.getCurrentAmount().toString()
            );
        }
    }

    public record DonationListItem(
            Long donationId,
            String title,
            String imageUrl,
            String description,
            String amount,
            String raised
    ) {
        public static DonationListItem from(Donation donation) {
            return new DonationListItem(
                    donation.getId(),
                    donation.getTitle(),
                    donation.getImageUrl(),
                    donation.getDescription(),
                    donation.getTargetAmount().toString(),
                    donation.getCurrentAmount().toString()
            );
        }
    }

    public static GetFilteredDonationResponse of(
            Donation recommendedDonation,
            Donation imminentDonation,
            Donation popularDonation,
            List<Donation> filteredDonations
    ) {
        return new GetFilteredDonationResponse(
                DonationSummary.from(recommendedDonation),
                DonationSummary.from(imminentDonation),
                DonationSummary.from(popularDonation),
                filteredDonations.stream().map(DonationListItem::from).toList()
        );
    }
}