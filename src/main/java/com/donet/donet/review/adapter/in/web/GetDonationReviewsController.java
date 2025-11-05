package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.review.application.port.in.GetDonationReviewsUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewsResponse;
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
public class GetDonationReviewsController {
    private final GetDonationReviewsUsecase getDonationReviewsUsecase;

    @GetMapping
    public BaseResponse<GetDonationReviewsResponse> getDonationReviews(@RequestParam("size") int size, Long lastId){
        log.info("[getDonationReviews] size = {}, lastId = {}", size, lastId);
        return new BaseResponse<>(getDonationReviewsUsecase.getDonationReviews(size, lastId));
    }
}
