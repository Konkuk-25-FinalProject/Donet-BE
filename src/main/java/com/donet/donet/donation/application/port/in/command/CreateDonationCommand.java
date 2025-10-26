package com.donet.donet.donation.application.port.in.command;

public record CreateDonationCommand (
        Long userId,
        Long donationId,
        Long donationAmount,
        boolean isAnonymous,
        String walletAddress
){
}
