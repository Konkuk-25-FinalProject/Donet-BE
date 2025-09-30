package com.donet.donet.donation.application.port.out;

import com.donet.donet.global.enums.Category;

import java.util.List;

public interface GetInterestedCategoriesPort {
    List<Category> findInterestedCategories(Long userId);
}
