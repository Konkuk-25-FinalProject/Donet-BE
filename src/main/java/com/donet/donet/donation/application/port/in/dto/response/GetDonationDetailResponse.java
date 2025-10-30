package com.donet.donet.donation.application.port.in.dto.response;

import com.donet.donet.donation.domain.Donation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record GetDonationDetailResponse(
        String title,
        String content,
        List<String> imgUrls,
        Long targetAmount,
        LocalDate startDate,
        LocalDate endDate,
        Long progressPercentage,
        List<Item> items
) {
    public record Item(
            String name,
            Long amount,
            Long price
    ){}

    public static GetDonationDetailResponse from(Donation donation) {
        List<Item> items = donation.getDonationItems()
                .stream()
                .map(entity -> new Item(entity.getName(), entity.getQuantity(), entity.getPrice()))
                .toList();

        return new GetDonationDetailResponse(
                donation.getTitle(),
                donation.getDescription(),
                donation.getImageUrl(),
                donation.getTargetAmount(),
                donation.getStartDate(),
                donation.getEndDate(),
                donation.getTargetAmount() == 0 ? 0L :
                        Math.min(100L, donation.getCurrentAmount() * 100 / donation.getTargetAmount()),
                items
        );
    }
}
