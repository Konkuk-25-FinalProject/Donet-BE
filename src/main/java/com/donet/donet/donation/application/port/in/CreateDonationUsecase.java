package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.dto.command.CreateDonationCommand;

public interface CreateDonationUsecase {
    void createDonation(CreateDonationCommand command);
}
