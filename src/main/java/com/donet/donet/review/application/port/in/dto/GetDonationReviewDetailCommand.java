package com.donet.donet.review.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetDonationReviewDetailCommand {
    private final Long donationReviewId;
}
