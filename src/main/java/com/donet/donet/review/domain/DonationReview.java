package com.donet.donet.review.domain;

import com.donet.donet.review.application.port.in.CreateDonationReviewCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DonationReview {
    private Long id;
    private String title;
    private String summary;
    private String imageUrl;
    private String content;

    public DonationReview(CreateDonationReviewCommand command, String imageUrl) {
        this(null, command.getTitle(), command.getSummary(), imageUrl, command.getContent());
    }
}
