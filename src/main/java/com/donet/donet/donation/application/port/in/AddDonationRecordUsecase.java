package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.command.AddDonationRecordCommand;

public interface AddDonationRecordUsecase {
    Object addDonationRecord(AddDonationRecordCommand command);
}
