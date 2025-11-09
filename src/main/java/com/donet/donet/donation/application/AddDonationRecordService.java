package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.AddDonationRecordUsecase;
import com.donet.donet.donation.application.port.in.dto.command.AddDonationRecordCommand;
import com.donet.donet.donation.application.port.out.CreateDonationRecordPort;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.UpdateDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.DonationRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddDonationRecordService implements AddDonationRecordUsecase {

    private final CreateDonationRecordPort createDonationRecordPort;
    private final UpdateDonationPort updateDonationPort;

    @Override
    public void addDonationRecord(AddDonationRecordCommand command) {
        updateDonationPort.addDonationAmount(command.donationId(), command.donationAmount());

        DonationRecord donationRecord = DonationRecord.builder()
                .userId(command.userId())
                .donationAmount(command.donationAmount())
                .donationId(command.donationId())
                .walletAddress(command.walletAddress())
                .build();

        createDonationRecordPort.createDonationRecord(donationRecord);
    }
}
