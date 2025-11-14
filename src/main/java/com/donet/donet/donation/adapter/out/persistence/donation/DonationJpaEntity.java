package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationItem.DonationItemJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerJpaEntity;
import com.donet.donet.global.exception.DonationException;
import com.donet.donet.global.persistence.BaseEntity;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.EXPIRED_DONATION;

@Table(name = "donation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class DonationJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean anonymous = false;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long targetAmount;

    @Column(nullable = false)
    private Long currentAmount;

    @Column(nullable = false)
    private Long views;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private PartnerJpaEntity partnerJpaEntity;

    @OneToMany(mappedBy = "donationJpaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationItemJpaEntity> donationItemJpaEntities;

    @OneToMany(mappedBy = "donationJpaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationImageJpaEntity> donationImageJpaEntities;

    @OneToMany(mappedBy = "donationJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationCategoryJpaEntity> donationCategories;

    public void increaseView(){
        if(this.views == null) this.views = 0L;
        this.views++;
    }

    public void addAmount(Long amount){
        if(this.currentAmount >= this.targetAmount || endDate.isBefore(LocalDate.now())) {
            throw new DonationException(EXPIRED_DONATION);
        }
        if(this.currentAmount + amount > this.targetAmount) {
            throw new DonationException(EXPIRED_DONATION);
        }
        this.currentAmount = this.currentAmount + amount;
    }

    public void addDonationItem(DonationItemJpaEntity donationItem) {
        if (this.donationItemJpaEntities == null) {
            this.donationItemJpaEntities = new ArrayList<>();
        }
        this.donationItemJpaEntities.add(donationItem);
    }

    public void addDonationImage(DonationImageJpaEntity donationImage) {
        if (this.donationImageJpaEntities == null) {
            this.donationImageJpaEntities = new ArrayList<>();
        }
        this.donationImageJpaEntities.add(donationImage);
    }

    public void addDonationCategory(DonationCategoryJpaEntity donationCategory) {
        if (this.donationCategories == null) {
            this.donationCategories = new ArrayList<>();
        }
        donationCategories.add(donationCategory);
        donationCategory.setDonationJpaEntity(this);
    }
}
