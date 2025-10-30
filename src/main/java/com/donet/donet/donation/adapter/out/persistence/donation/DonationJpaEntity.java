package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.donation.adapter.out.persistence.PartnerJpaEntity;
import com.donet.donet.global.persistence.BaseEntity;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    public void increaseView(){
        if(this.views == null) this.views = 0L;
        this.views++;
    }
}
