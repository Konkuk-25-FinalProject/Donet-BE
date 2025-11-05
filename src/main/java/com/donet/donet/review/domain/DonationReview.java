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
    private Long writerId;
    private String writerName;
    private String summary;
    private List<String> tags;
    private String imageUrl;
    private String content;
    private Long donationId;

    public DonationReview(CreateDonationReviewCommand command, String imageUrl) {
        this(null, command.getTitle(), command.getUserId(),
                null, command.getSummary(), command.getTags(),
                imageUrl, command.getContent(), command.getDonationId());
    }
}
