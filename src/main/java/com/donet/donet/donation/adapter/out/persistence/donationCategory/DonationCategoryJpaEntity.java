package com.donet.donet.donation.adapter.out.persistence.donationCategory;

import com.donet.donet.donation.adapter.out.persistence.category.CategoryJpaEntity;
import com.donet.donet.donation.adapter.out.persistence.donation.DonationJpaEntity;
import com.donet.donet.global.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donation_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationCategoryJpaEntity extends BaseEntity {
    //Donation과 Category의 중간테이블 엔티티 클래스
    //ManyToMany 관계를 해결하기 위해 중간테이블을 엔티티로 승격
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private DonationJpaEntity donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpaEntity category;
}

