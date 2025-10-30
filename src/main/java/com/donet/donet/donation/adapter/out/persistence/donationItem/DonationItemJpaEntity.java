package com.donet.donet.donation.adapter.out.persistence.donationItem;

import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.donation.domain.DonationItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "donation_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class DonationItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
    private DonationJpaEntity donationJpaEntity;

    public static DonationItemJpaEntity createNewEntity(DonationItem item) {
        return DonationItemJpaEntity.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }
}
