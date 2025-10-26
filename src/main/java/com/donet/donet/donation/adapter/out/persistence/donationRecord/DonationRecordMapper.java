package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationRepository;
import com.donet.donet.donation.domain.DonationRecord;
import com.donet.donet.global.exception.DonationException;
import com.donet.donet.global.exception.UserException;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.NO_MATCH_DONATION;
import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class DonationRecordMapper {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    public DonationRecordJpaEntity toJpaEntity(DonationRecord donationRecord) {
        UserJpaEntity userJpaEntity = userRepository.findById(donationRecord.getUserId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        DonationJpaEntity donationJpaEntity = donationRepository.findDonationById(donationRecord.getDonationId())
                .orElseThrow(() -> new DonationException(NO_MATCH_DONATION));

        return DonationRecordJpaEntity.builder()
                .donationAmount(donationRecord.getDonationAmount())
                .userJpaEntity(userJpaEntity)
                .donationJpaEntity(donationJpaEntity)
                .build();
    }
}
