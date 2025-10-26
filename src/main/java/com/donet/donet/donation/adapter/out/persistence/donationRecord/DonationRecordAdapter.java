package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import com.donet.donet.donation.application.port.out.CreateDonationRecordPort;
import com.donet.donet.donation.domain.DonationRecord;
import org.springframework.stereotype.Component;

@Component
public class DonationRecordAdapter implements CreateDonationRecordPort {
    @Override
    public boolean createDonationRecord(DonationRecord donationRecord) {
        return false;
    }
}
