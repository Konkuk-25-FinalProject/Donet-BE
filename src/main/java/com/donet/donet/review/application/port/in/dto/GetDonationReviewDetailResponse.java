package com.donet.donet.review.application.port.in.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetDonationReviewDetailResponse {
    private final String title;
    private final List<String> tags;
    private final String writerName;
    private final String imageUrl;
    private final String content;
}
