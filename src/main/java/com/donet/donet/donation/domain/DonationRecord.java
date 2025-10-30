package com.donet.donet.donation.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DonationRecord {
    private Long id;
    private Long donationAmount;
    private Long userId;
    private Long donationId;
    private String walletAddress;
}
