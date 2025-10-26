package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.AddDonationRecordUsecase;
import com.donet.donet.donation.application.port.in.command.AddDonationRecordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddDonationRecordService implements AddDonationRecordUsecase {

    @Override
    public void addDonationRecord(AddDonationRecordCommand command) {

    }
}
