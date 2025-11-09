package com.donet.donet.user.adapter.in.web.dto;

import com.donet.donet.user.application.port.in.dto.GetMyPageResponse;

import java.util.List;

public record GetMyPageApiResponse (
    String nickname,
    String walletAddress,
    String profileImageUrl,
    List<JoinedDonation> joinedDonations,
    List<RegisteredDonation> registeredDonations
){
    public static GetMyPageApiResponse from(GetMyPageResponse resp) {
        return new GetMyPageApiResponse(resp.nickname(),
                resp.profileImageUrl(),
                resp.walletAddress(),
                resp.joinedDonations().stream()
                        .map(j -> new JoinedDonation(j.donationId(), j.title(), j.imageUrl(), j.amount()))
                        .toList(),
                resp.registeredDonations().stream()
                        .map(r -> new RegisteredDonation(r.donationId(), r.title(), r.imageUrl(), r.amount(), r.progressRate(), r.reviewable()))
                        .toList()
                );
    }

    public record JoinedDonation(
                Long donationId,
                String title,
                String imageUrl,
                Long amount
        ){
        }

        public record RegisteredDonation (
                Long donationId,
                String title,
                String imageUrl,
                Long amount,
                Long progressRate,
                Boolean reviewable
        ){
        }
}
