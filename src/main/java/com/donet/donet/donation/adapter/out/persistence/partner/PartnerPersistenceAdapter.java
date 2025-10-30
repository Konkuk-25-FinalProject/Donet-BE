package com.donet.donet.donation.adapter.out.persistence.partner;

import com.donet.donet.donation.application.port.out.FindPartnerPort;
import com.donet.donet.donation.domain.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PartnerPersistenceAdapter implements FindPartnerPort {
    private final PartnerRepository partnerRepository;

    @Override
    public List<Partner> findAllPartners() {
        List<PartnerJpaEntity> partnerJpaEntities = partnerRepository.findAll();
        return partnerJpaEntities.stream()
                .map(entity -> new Partner(entity.getId(), entity.getName(), entity.getWalletAddress()))
                .toList();
    }
}
