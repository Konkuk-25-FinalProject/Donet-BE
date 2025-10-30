package com.donet.donet.donation.application.port.in.dto.response;

import com.donet.donet.donation.domain.Donation;

import java.time.LocalDate;
import java.util.List;

public record GetDonationDetailResponse(
        String title,
        String content,
        List<String> imgUrls,
        Long targetAmount,
        LocalDate startDate,
        LocalDate endDate,
        Long progressPercentage
) {
    public static GetDonationDetailResponse from(Donation donation) {
        return new GetDonationDetailResponse(
                donation.getTitle(),
                donation.getDescription(),
                donation.getImageUrl(),
                donation.getTargetAmount(),
                donation.getStartDate(),
                donation.getEndDate(),
                donation.getTargetAmount() == 0 ? 0L :
                        Math.min(100L, donation.getCurrentAmount() * 100 / donation.getTargetAmount())
        );
    }
}
