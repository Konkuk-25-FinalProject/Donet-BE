package com.donet.donet.review.application.port.in;

import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailCommand;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewDetailResponse;

public interface GetDonationReviewDetailUsecase {
    GetDonationReviewDetailResponse getDonationReviewDetail(GetDonationReviewDetailCommand command);
}
