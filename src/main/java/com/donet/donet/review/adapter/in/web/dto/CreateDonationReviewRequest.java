package com.donet.donet.review.adapter.in.web.dto;


import com.donet.donet.review.application.port.in.CreateDonationReviewCommand;
import org.springframework.web.multipart.MultipartFile;

public record CreateDonationReviewRequest (
        String title,
        String summary,
        String content
){
    public CreateDonationReviewCommand toCommand(MultipartFile reviewImage) {
        return new CreateDonationReviewCommand(title, summary, null, content, reviewImage);
    }
}
