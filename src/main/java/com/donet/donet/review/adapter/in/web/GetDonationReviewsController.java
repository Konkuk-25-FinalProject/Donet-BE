package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.global.swagger.SwaggerResponseDescription;
import com.donet.donet.review.application.port.in.GetDonationReviewsUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("reviews")
@RestController
public class GetDonationReviewsController implements DonationReviewController{
    private final GetDonationReviewsUsecase getDonationReviewsUsecase;

    @Operation(
            summary = "기부후기 리스트 조회",
            description = "기부후기 리스트를 조회할 수 있다"
    )
    @CustomExceptionDescription(SwaggerResponseDescription.GET_DONATION_REVIEW)
    @GetMapping
    public BaseResponse<GetDonationReviewsResponse> getDonationReviews(@RequestParam("size") int size, @RequestParam("lastId")Long lastId){
        log.info("[getDonationReviews] size = {}, lastId = {}", size, lastId);
        return new BaseResponse<>(getDonationReviewsUsecase.getDonationReviews(size, lastId));
    }
}
