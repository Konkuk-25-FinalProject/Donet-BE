package com.donet.donet.donation.application.port.in.dto.response;

import java.util.List;

public record GetPartnerResponse(
        List<Partner> partners
) {
    public record Partner(
            Long partnerId,
            String name
    ){}
}
