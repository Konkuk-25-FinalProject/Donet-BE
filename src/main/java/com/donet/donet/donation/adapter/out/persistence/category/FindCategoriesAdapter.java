package com.donet.donet.donation.adapter.out.persistence.category;

import com.donet.donet.donation.application.port.out.GetInterestedCategoriesPort;
import com.donet.donet.donation.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetInterestedCategoriesAdapter implements GetInterestedCategoriesPort {
    private final CategoriesRepository categoriesRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> findInterestedCategories(Long userId) {
        return categoriesRepository.findInterestedCategories(userId)
                .stream()
                .map(categoryMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public List<Category> findCategoriesByName(List<String> names) {
        return categoriesRepository.findCategoriesByName(names)
                .stream()
                .map(categoryMapper::mapToDomainEntity)
                .toList();
    }
}
