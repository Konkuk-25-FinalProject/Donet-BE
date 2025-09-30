package com.donet.donet.donation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Donation {
    private Long id;
    private String title;
    private String description;
    private Boolean anonymous;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long targetAmount;
    private Long currentAmount;
    private Long views;
    private String imageUrl;
    private Long userId;
    private Long partnerId;
}
