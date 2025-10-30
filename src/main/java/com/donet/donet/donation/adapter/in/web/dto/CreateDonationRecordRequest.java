package com.donet.donet.donation.adapter.in.web.dto;

public record CreateDonationRecordRequest(
        Long donationId,
        Long donationAmount,
        boolean isAnonymous,
        String walletAddress
) {
}
