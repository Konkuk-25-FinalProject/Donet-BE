package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Partner;

import java.util.List;

public interface FindPartnerPort {
    List<Partner> findAllPartners();
}
