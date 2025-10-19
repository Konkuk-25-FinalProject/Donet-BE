package com.donet.donet.donation.application.port.out;

import com.donet.donet.donation.domain.Category;

import java.util.List;

public interface FindCategoriesPort {
    List<Category> findInterestedCategories(Long userId);
    List<Category> findCategoriesByName(List<String> names);
}
