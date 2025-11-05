package com.donet.donet.review.application.port.in.dto;

import java.util.List;

public record GetDonationReviewsResponse (
    List<ReviewSummary> reviews,
    Long lastId,
    Boolean hasNext
){
    public record ReviewSummary(
            Long donationReviewId,
            String title,
            List<String> tags,
            String writer,
            String imageUrl,
            String content
    ){

    }
}
