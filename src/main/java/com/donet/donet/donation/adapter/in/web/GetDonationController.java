package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.GetDonationDetailUsecase;
import com.donet.donet.donation.application.port.in.response.GetDonationDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class GetDonationController implements DonationController{
    private final GetDonationDetailUsecase getDonationDetailUsecase;

    @GetMapping("/{donationId}/detail")
    public GetDonationDetailResponse getDonationDetail(@PathVariable long donationId) {
        return getDonationDetailUsecase.getDonationDetail(donationId);
    }
}
