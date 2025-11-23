package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.domain.DonationRecord;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DonationRecordMapper {

    public DonationRecordJpaEntity toJpaEntity(
            DonationRecord donationRecord,
            UserJpaEntity userJpaEntity,
            DonationJpaEntity donationJpaEntity) {
        return DonationRecordJpaEntity.builder()
                .donationAmount(donationRecord.getDonationAmount())
                .userJpaEntity(userJpaEntity)
                .donationJpaEntity(donationJpaEntity)
                .walletAddress(donationRecord.getWalletAddress())
                .build();
    }
}
