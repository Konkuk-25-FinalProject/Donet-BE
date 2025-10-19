package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.response.GetDonationDetailResponse;

public interface GetDonationDetailUsecase {
    GetDonationDetailResponse getDonationDetail(String donationId);
}
