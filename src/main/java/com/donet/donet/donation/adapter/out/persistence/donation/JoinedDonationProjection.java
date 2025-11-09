package com.donet.donet.donation.adapter.out.persistence.donation;

public interface JoinedDonationProjection {
    Long getDonationId();
    String getTitle();
    String getImageUrl();
    Long getDonatedAmount();
}
