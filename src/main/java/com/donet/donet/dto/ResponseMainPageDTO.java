package com.donet.donet.dto;

import java.util.List;

public record ResponseMainPageDTO(
        List<Donation> topDonation,
        List<Donation> recommendDonations,
        GoodNews goodNews
) {
    public record GoodNews(
            String title,
            String content,
            String imgUrl
    ){}

    public record Donation(
            String title,
            String content,
            long price,
            long totalCoin,
            String imgUrl,
            long gatheredCoin,
            String doneeName
    ){}
}
