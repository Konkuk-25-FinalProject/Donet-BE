package com.donet.donet.donation.adapter.in.web.dto;

import com.donet.donet.donation.application.port.in.command.CreateDonationCommand;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CreateDonationCommand.Item> toCommandItem() {
        if(this.items == null || this.items.isEmpty()) {
            return List.of();
        }
        return this.items.stream()
                .map(item -> new CreateDonationCommand.Item(item.itemName, item.amount, item.price))
                .toList();
    }
}
