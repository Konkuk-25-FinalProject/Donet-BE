package com.donet.donet.review.application.port.in;

import com.donet.donet.review.application.port.in.dto.GetDonationReviewsResponse;

public interface GetDonationReviewsUsecase {
    GetDonationReviewsResponse getDonationReviews(int size, Long lastId);
}
