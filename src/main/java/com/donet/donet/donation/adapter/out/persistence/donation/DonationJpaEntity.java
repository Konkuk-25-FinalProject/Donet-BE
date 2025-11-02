package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.adapter.out.persistence.category.CategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationCategory.DonationCategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donationItem.DonationItemJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.partner.PartnerJpaEntity;
import com.donet.donet.global.persistence.BaseEntity;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "donation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private PartnerJpaEntity partnerJpaEntity;

    @OneToMany(mappedBy = "donation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationItemJpaEntity> donationItemJpaEntities;

    @OneToMany(mappedBy = "donation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationImageJpaEntity> donationImageJpaEntities;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonationCategoryJpaEntity> donationCategories;

    public void increaseView(){
        if(this.views == null) this.views = 0L;
        this.views++;
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
        donationCategory.setDonation(this);
    }
}
