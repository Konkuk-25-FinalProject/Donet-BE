package com.donet.donet.donation.application.port.in.dto.command;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record CreateDonationCommand(
        Long userId,
        List<MultipartFile> images,
        String title,
        boolean isAnonymous,
        List<Item> items,
        LocalDate startDate,
        LocalDate endDate,
        List<String> category,
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
