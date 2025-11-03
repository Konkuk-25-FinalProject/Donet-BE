package com.donet.donet.review.domain;

import com.donet.donet.review.application.port.in.dto.CreateDonationReviewCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DonationReview {
    private Long id;
    private String title;
    private String summary;
    private List<String> tags;
    private String imageUrl;
    private String content;

    public DonationReview(CreateDonationReviewCommand command, String imageUrl) {
        this(null, command.getTitle(), command.getSummary(), command.getTags(), imageUrl, command.getContent());
    }
}
