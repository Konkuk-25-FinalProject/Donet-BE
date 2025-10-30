package com.donet.donet.donation.application.port.in.dto.command;

public record AddDonationRecordCommand(
        Long userId,
        Long donationId,
        Long donationAmount,
        boolean isAnonymous,
        String walletAddress
){
}
