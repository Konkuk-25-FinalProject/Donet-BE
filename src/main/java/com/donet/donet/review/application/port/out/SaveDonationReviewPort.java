package com.donet.donet.review.application.port.out;

import com.donet.donet.review.domain.DonationReview;

public interface SaveDonationReviewPort {
    DonationReview save(DonationReview donationReview);
}
