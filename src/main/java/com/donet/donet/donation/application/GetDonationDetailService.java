package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.GetDonationDetailUsecase;
import com.donet.donet.donation.application.port.in.response.GetDonationDetailResponse;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.UpdateDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.exception.DonationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.FAIL_TO_LOAD_DONATION_DETAIL;

@Service
@RequiredArgsConstructor
public class GetDonationDetailService implements GetDonationDetailUsecase {
    private final FindDonationPort findDonationPort;
    private final UpdateDonationPort updateDonationPort;

    @Override
    public GetDonationDetailResponse getDonationDetail(Long donationId) {
        Donation donation;
        try{
            updateDonationPort.increaseDonationView(donationId);
            donation = findDonationPort.findDonationById(donationId);
        }catch (DonationException e){
            throw new DonationException(FAIL_TO_LOAD_DONATION_DETAIL);
        }

        return GetDonationDetailResponse.from(donation);
    }
}
