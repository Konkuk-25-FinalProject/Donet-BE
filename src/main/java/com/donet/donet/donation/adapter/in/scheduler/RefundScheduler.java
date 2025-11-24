package com.donet.donet.donation.adapter.in.scheduler;

import com.donet.donet.donation.application.port.in.RefundDonationUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefundScheduler {
    private final RefundDonationUsecase refundDonationUsecase;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void runRefundJob() {
        refundDonationUsecase.refundDonation();
    }
}
