package com.donet.donet.review.adapter.out.persistence;

import com.donet.donet.global.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation_review")
@Entity
public class DonationReviewJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String tags;

    @Lob
    private String content;

    private String imageUrl;
}
