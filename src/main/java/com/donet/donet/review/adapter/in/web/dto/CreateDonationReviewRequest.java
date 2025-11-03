package com.donet.donet.review.adapter.in.web.dto;


import com.donet.donet.review.application.port.in.dto.CreateDonationReviewCommand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateDonationReviewRequest (
        Long donationId,
        String title,
        String summary,
        List<String> tags,
        String content
){
    public CreateDonationReviewCommand toCommand(Long userId, MultipartFile reviewImage) {
        return new CreateDonationReviewCommand(userId, donationId, title, summary, tags, content, reviewImage);
    }
}
