package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.review.application.port.in.GetDonationReviewDetailUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailCommand;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("reviews")
@RestController
public class GetDonationReviewController {
    private final GetDonationReviewDetailUsecase getDonationReviewDetailUsecase;

    @GetMapping("/{donationReviewId}/detail")
    public BaseResponse<GetDonationReviewDetailResponse> getDonationReview(@PathVariable Long donationReviewId){
        GetDonationReviewDetailCommand commmand = new GetDonationReviewDetailCommand(donationReviewId);
        return new BaseResponse<>(getDonationReviewDetailUsecase.getDonationReviewDetail(commmand));
    }
}
