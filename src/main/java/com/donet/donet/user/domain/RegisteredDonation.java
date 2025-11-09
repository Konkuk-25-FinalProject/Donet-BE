package com.donet.donet.user.domain;

import java.util.List;

public record RegisteredDonation (
            Long donationId,
            String title,
            String imageUrl,
            Long amount,
            Long progressRate,
            Boolean reviewable
){
    public static RegisteredDonation of(Long donationId, String title, List<String> imageUrls, Long targetAmount, Long currentAmount, boolean reviewable) {
        return new RegisteredDonation(donationId,
                title,
                imageUrls.isEmpty() ? null : imageUrls.get(0),
                targetAmount,
                (long)(((double) currentAmount / targetAmount) * 100),
                reviewable);
    }
}