package com.donet.donet.donation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
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
    private List<String> imageUrl;
    private Long userId;
    private Long partnerId;
}
