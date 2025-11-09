package com.donet.donet.user.domain;

public record JoinedDonation(
        Long donationId,
        String title,
        String imageUrl,
        Long amount
){
}