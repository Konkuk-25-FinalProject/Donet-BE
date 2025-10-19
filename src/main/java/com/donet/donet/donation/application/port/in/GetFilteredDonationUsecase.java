package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.response.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.command.GetFilteredDonationCommand;

public interface GetFilteredDonationUsecase {
    GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command);
}
