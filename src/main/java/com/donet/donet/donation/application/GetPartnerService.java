package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.GetPartnerUsecase;
import com.donet.donet.donation.application.port.in.dto.response.GetPartnerResponse;
import com.donet.donet.donation.application.port.out.FindPartnerPort;
import com.donet.donet.donation.domain.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPartnerService implements GetPartnerUsecase {
    private final FindPartnerPort findPartnerPort;

    @Override
    public GetPartnerResponse getPartner() {
        List<Partner> partners = findPartnerPort.findAllPartners();
        return GetPartnerResponse.from(partners);
    }
}
