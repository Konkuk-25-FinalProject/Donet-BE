package com.donet.donet.review.application.port.out;

import com.donet.donet.review.domain.DonationReview;

import java.util.List;
import java.util.Optional;

public interface LoadDonationReviewPort {
    List<DonationReview> loadRecentReviews(Integer limit);
    Optional<DonationReview> load(Long donationReviewId);
}
