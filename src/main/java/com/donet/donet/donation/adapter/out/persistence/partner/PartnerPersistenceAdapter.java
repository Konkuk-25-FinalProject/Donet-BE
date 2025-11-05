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
    private final PartnerMapper partnerMapper;

    @Override
    public List<Partner> findAllPartners() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::mapToDomainEntity)
                .toList();
    }
}
