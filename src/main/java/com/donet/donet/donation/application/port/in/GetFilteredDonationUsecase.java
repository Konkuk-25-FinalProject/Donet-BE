package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.adapter.in.web.dto.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.command.GetFilteredDonationCommand;

public interface GetFilteredDonationUsecase {
    GetFilteredDonationResponse getFilteredDonation(GetFilteredDonationCommand command);
}
