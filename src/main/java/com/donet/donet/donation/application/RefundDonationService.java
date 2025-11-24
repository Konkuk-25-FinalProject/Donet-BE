package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.RefundDonationUsecase;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.SmartContractPort;
import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundDonationService implements RefundDonationUsecase {

    private final FindDonationPort findDonationPort;
    private final SmartContractPort smartContractPort;

    @Override
    public void refundDonation() {
        List<Long> refundTargetDonationIds = findDonationPort.findRefundableDonation()
                .stream()
                .map(Donation::getId)
                .toList();

        smartContractPort.refundDonations(refundTargetDonationIds);
    }
}
