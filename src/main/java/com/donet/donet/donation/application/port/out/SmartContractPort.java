package com.donet.donet.donation.application.port.out;

import java.util.List;

public interface SmartContractPort {
    boolean refundDonations(List<Long> donationIds);
}
