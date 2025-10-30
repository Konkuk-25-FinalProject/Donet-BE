package com.donet.donet.donation.application.port.in.command;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record CreateDonationCommand(
        List<MultipartFile> images,
        String title,
        boolean isAnonymous,
        List<Item> items,
        String address,
        LocalDate startDate,
        LocalDate endDate,
        String category,
        Long targetAmount,
        Long partnerId,
        String content
) {
    public record Item(
            String itemName,
            Long amount
    ){
    }
}
