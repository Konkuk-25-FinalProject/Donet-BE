package com.donet.donet.review.application.port.in;

import com.donet.donet.review.domain.DonationReview;

public interface CreateDonationReviewUsecase {
    DonationReview create(CreateDonationReviewCommand command);
}
