package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import com.donet.donet.donation.application.port.out.CreateDonationRecordPort;
import com.donet.donet.donation.domain.DonationRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DonationRecordAdapter implements CreateDonationRecordPort {
    private final DonationRecordRepository donationRecordRepository;
    private final DonationRecordMapper donationRecordMapper;

    @Override
    public boolean createDonationRecord(DonationRecord donationRecord) {
        try {
            DonationRecordJpaEntity donationRecordJpaEntity = donationRecordMapper.toJpaEntity(donationRecord);
            donationRecordRepository.save(donationRecordJpaEntity);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
