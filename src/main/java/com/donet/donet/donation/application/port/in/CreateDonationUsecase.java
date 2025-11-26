package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.adapter.in.web.dto.CreateDonationResponse;
import com.donet.donet.donation.application.port.in.dto.command.CreateDonationCommand;

public interface CreateDonationUsecase {
    CreateDonationResponse createDonation(CreateDonationCommand command);
}
