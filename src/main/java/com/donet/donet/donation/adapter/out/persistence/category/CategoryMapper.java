package com.donet.donet.donation.adapter.out.persistence.category;

import com.donet.donet.donation.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category mapToDomainEntity(CategoryJpaEntity categoryJpaEntity) {
        return new Category(
                categoryJpaEntity.getId(),
                categoryJpaEntity.getName()
        );
    }

    public CategoryJpaEntity mapToJpaEntity(Category category) {
        return CategoryJpaEntity.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
    }
}
