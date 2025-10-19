package com.donet.donet.donation.application.port.in.response;

import java.time.LocalDate;
import java.util.List;

public record GetDonationDetailResponse(
        String title,
        String content,
        List<String> imgUrls,
        Long targetAmount,
        LocalDate startDate,
        LocalDate endDate,
        Integer progressPercentage
) {
}
