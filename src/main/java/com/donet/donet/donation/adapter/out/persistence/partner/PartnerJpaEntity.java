package com.donet.donet.donation.adapter.out.persistence.partner;

import com.donet.donet.global.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "donation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class PartnerJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String walletAddress;
}
