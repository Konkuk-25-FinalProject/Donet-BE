package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.dto.response.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.dto.command.GetFilteredDonationCommand;

public interface GetFilteredDonationUsecase {
    GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command);
}
