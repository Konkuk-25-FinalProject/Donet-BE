package com.donet.donet.donation.application.port.in.dto.response;

import com.donet.donet.donation.domain.Partner;

import java.util.List;

public record GetPartnerResponse(
        List<PartnerDto> partners
) {
    public record PartnerDto(
            Long partnerId,
            String name
    ){}

    public static GetPartnerResponse from(List<Partner> partners) {
        List<PartnerDto> partnerDto = partners.stream()
                .map(partner -> new PartnerDto(partner.getId(), partner.getName()))
                .toList();

        return new GetPartnerResponse(partnerDto);
    }
}
