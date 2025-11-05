package com.donet.donet.review.application;

import com.donet.donet.global.exception.CustomException;
import com.donet.donet.review.application.port.in.GetDonationReviewDetailUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailCommand;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailResponse;
import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.DONATION_REVIEW_NOT_FOUND;

public class GetDonationReviewDetailService implements GetDonationReviewDetailUsecase {
    private LoadDonationReviewPort loadDonationReviewPort;
    @Override
    public GetDonationReviewDetailResponse getDonationReviewDetail(GetDonationReviewDetailCommand command) {
        DonationReview donationReview = loadDonationReviewPort.load(command.getDonationReviewId())
                .orElseThrow(() -> new CustomException(DONATION_REVIEW_NOT_FOUND));

        return new GetDonationReviewDetailResponse(donationReview.getTitle(),
                donationReview.getTags(),
                donationReview.getWriterName(),
                donationReview.getImageUrl(),
                donationReview.getContent());
    }
}
