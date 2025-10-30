package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "donation_record")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class DonationRecordJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long donationAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private DonationJpaEntity donationJpaEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private WalletJpaEntity walletJpaEntity;
}
