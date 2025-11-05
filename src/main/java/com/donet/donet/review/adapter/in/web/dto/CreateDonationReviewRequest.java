package com.donet.donet.review.adapter.in.web.dto;


import com.donet.donet.review.application.port.in.dto.CreateDonationReviewCommand;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateDonationReviewRequest (
        Long donationId,
        @NotBlank String title,
        @NotBlank String summary,
        List<@NotBlank String> tags,
        @NotBlank String content
){
    public CreateDonationReviewCommand toCommand(Long userId, MultipartFile reviewImage) {
        return new CreateDonationReviewCommand(userId, donationId, title, summary, tags, content, reviewImage);
    }
}
