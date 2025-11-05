package com.donet.donet.donation.adapter.out.persistence.partner;

import com.donet.donet.donation.domain.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {
    public Partner mapToDomainEntity(PartnerJpaEntity partnerJpaEntity) {
        return new Partner(
                partnerJpaEntity.getId(),
                partnerJpaEntity.getName(),
                partnerJpaEntity.getWalletAddress()
        );
    }
}
