package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.RefundDonationUsecase;
import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.application.port.out.SmartContractPort;
import com.donet.donet.donation.domain.Donation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

        if (refundTargetDonationIds.isEmpty()) {
            log.info("환불 대상 기부가 없습니다.");
            return;
        }

        log.info("환불 처리 시작: {} 건", refundTargetDonationIds.size());
        boolean success = smartContractPort.refundDonations(refundTargetDonationIds);

        if (success) {
            log.info("환불 처리 완료: {} 건", refundTargetDonationIds.size());
        }else{
            log.error("환불 처리 실패: donation IDs = {}", refundTargetDonationIds);
        }
    }
}
