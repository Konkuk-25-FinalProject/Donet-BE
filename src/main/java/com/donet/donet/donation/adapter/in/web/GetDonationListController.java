package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.response.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.GetFilteredDonationUsecase;
import com.donet.donet.donation.application.port.in.command.GetFilteredDonationCommand;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class GetDonationListController implements DonationController{
    private final GetFilteredDonationUsecase getFilteredDonationUsecase;

    @GetMapping("")
    public BaseResponse<GetFilteredDonationResponse> getDonationList(@CurrentUserId Long userId, @RequestParam("category")List<String> categories, Pageable pageable) {
        GetFilteredDonationCommand command = new GetFilteredDonationCommand(userId, categories, pageable);

        return new BaseResponse<>(getFilteredDonationUsecase.getFilteredDonation(command));
    }
}
