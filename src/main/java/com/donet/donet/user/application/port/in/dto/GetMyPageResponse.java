package com.donet.donet.user.application.port.in.dto;

import com.donet.donet.user.domain.JoinedDonation;
import com.donet.donet.user.domain.RegisteredDonation;

import java.util.List;

public record GetMyPageResponse (
        String nickname,
        String walletAddress,
        String profileImageUrl,
        List<JoinedDonation> joinedDonations,
        List<RegisteredDonation> registeredDonations
){
}
