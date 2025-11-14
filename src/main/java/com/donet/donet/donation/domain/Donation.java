package com.donet.donet.donation.domain;

import com.donet.donet.global.exception.DonationException;
import com.donet.donet.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.EXPIRED_DONATION;

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
    private List<DonationItem> donationItems;
    private List<Category> categories;

    public boolean isWriter(User user) {
        return userId == user.getId();
    }

    public void addAmount(Long amount){
        if(currentAmount >= targetAmount || endDate.isBefore(LocalDate.now())) {
            throw new DonationException(EXPIRED_DONATION);
        }
        if(currentAmount + amount > targetAmount) {
            throw new DonationException(EXPIRED_DONATION);
        }
        currentAmount = currentAmount + amount;
    }
}
