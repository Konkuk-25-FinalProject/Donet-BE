package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.review.adapter.in.web.dto.CreateDonationReviewRequest;
import com.donet.donet.review.application.port.in.CreateDonationReviewUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("reviews")
@RestController
public class CreateDonationReviewController {
    private final CreateDonationReviewUsecase createDonationReviewUsecase;
    @PostMapping
    public BaseResponse<Void> createDonationReview(@RequestPart CreateDonationReviewRequest reviewRequest,
                                                   @RequestPart(required = false) MultipartFile reviewImage){
        createDonationReviewUsecase.create(reviewRequest.toCommand(reviewImage));
        return new BaseResponse<>(null);
    }
}
