package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.DonationRecord;

public interface CreateDonationRecordPort {
    boolean createDonationRecord(DonationRecord donationRecord);
}
