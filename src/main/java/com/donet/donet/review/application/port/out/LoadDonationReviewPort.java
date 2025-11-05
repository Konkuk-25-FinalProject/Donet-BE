package com.donet.donet.review.application.port.out;

import com.donet.donet.review.domain.DonationReview;

import java.util.List;

public interface LoadDonationReviewPort {
    List<DonationReview> loadRecentReviews();
}
