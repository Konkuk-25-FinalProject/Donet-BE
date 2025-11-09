package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.global.swagger.SwaggerResponseDescription;
import com.donet.donet.review.application.port.in.GetDonationReviewDetailUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailCommand;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("reviews")
@RestController
public class GetDonationReviewController implements DonationReviewController{
    private final GetDonationReviewDetailUsecase getDonationReviewDetailUsecase;

    @Operation(
            summary = "기부후기 상세 조회",
            description = "기부후기를 상세 조회할 수 있다"
    )
    @CustomExceptionDescription(SwaggerResponseDescription.GET_DONATION_REVIEW)
    @GetMapping("/{donationReviewId}/detail")
    public BaseResponse<GetDonationReviewDetailResponse> getDonationReview(@PathVariable Long donationReviewId){
        GetDonationReviewDetailCommand command = new GetDonationReviewDetailCommand(donationReviewId);
        return new BaseResponse<>(getDonationReviewDetailUsecase.getDonationReviewDetail(command));
    }
}
