package com.donet.donet.donation.adapter.out.persistence.category;

import com.donet.donet.global.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CategoryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
