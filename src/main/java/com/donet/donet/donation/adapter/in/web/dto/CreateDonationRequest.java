package com.donet.donet.donation.adapter.in.web.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateDonationRequest(
        String title,
        boolean isAnonymous,
        List<Item> items,
        LocalDate startDate,
        LocalDate endDate,
        String category,
        Long targetAmount,
        Long partnerId,
        String content
) {
    public record Item(
            String itemName,
            Long amount,
            Long price
    ){
    }
}
