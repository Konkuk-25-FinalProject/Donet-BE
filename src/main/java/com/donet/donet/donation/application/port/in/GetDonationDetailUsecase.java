package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.dto.response.GetDonationDetailResponse;

public interface GetDonationDetailUsecase {
    GetDonationDetailResponse getDonationDetail(Long donationId);
}
