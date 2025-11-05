package com.donet.donet.review.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.review.adapter.in.web.dto.CreateDonationReviewRequest;
import com.donet.donet.review.application.port.in.CreateDonationReviewUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.CREATE_DONATION_REVIEW;

@RequiredArgsConstructor
@RequestMapping("reviews")
@RestController
public class CreateDonationReviewController implements DonationReviewController{
    private final CreateDonationReviewUsecase createDonationReviewUsecase;

    @Operation(
            summary = "기부후기 생성",
            description = "기부후기를 생성할 수 있다. 기부후기에서 이미지는 선택사항이다."
    )
    @CustomExceptionDescription(CREATE_DONATION_REVIEW)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse<Void> createDonationReview(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                   @Parameter @RequestPart @Validated CreateDonationReviewRequest reviewRequest,
                                                   @Parameter @RequestPart(required = false) MultipartFile reviewImage){
        createDonationReviewUsecase.create(reviewRequest.toCommand(userId, reviewImage));
        return new BaseResponse<>(null);
    }
}
