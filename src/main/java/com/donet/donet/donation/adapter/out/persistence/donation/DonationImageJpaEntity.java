package com.donet.donet.donation.adapter.out.persistence.donation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "donation_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class DonationImageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DonationJpaEntity donationJpaEntity;
}
