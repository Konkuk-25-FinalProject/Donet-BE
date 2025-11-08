package com.donet.donet.user.application.port.in.dto;

import java.util.List;

public record GetMyPageResponse (
        String nickname,
        String walletAddress,
        String profileImageUrl,
        List<JoinedDonation> joinedDonations,
        List<RegisteredDonation> registeredDonations
){
    public record JoinedDonation(
            String title,
            String imageUrl,
            Long amount
    ){
    }

    public record RegisteredDonation (
            String title,
            String imageUrl,
            Long amount,
            Long progressRate,
            Boolean reviewable
    ){
    }
}
