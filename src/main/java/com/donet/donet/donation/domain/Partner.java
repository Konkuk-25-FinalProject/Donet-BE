package com.donet.donet.donation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Partner {
    private Long id;
    private String name;
    private String walletAddress;
}
