package com.donet.donet.donation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DonationItem {
    private long id;
    private String name;
    private Long quantity;
    private Long price;
}
