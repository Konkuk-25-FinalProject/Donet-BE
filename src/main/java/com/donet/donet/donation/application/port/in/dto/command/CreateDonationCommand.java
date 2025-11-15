package com.donet.donet.donation.application.port.in.dto.command;

import com.donet.donet.global.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.TARGET_AMOUNT_LOWER_BOUND_LIMIT;

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
    public static CreateDonationCommand from(Long userId, List<MultipartFile> images, String title, boolean isAnonymous, List<Item> items, LocalDate startDate, LocalDate endDate, List<String> category, Long targetAmount, Long partnerId, String content) {
        if(targetAmount <= 0){
            throw new CustomException(TARGET_AMOUNT_LOWER_BOUND_LIMIT);
        }
        return new CreateDonationCommand(userId, images, title, isAnonymous, items, startDate, endDate, category, targetAmount, partnerId, content);
    }

    public record Item(
            String itemName,
            Long quantity,
            Long price
    ){
    }

}
