package com.donet.donet.donation.domain;

import java.time.LocalDate;

public class Donation {
    private Long id;
    private String title;
    private String description;
    private Boolean anonymous;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long targetAmount;
    private Long currentAmount;
    private Long userId;
    private Long partnerId;
}
