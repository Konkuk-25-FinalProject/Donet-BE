package com.donet.donet.donation.adapter.out.jpa;

import com.donet.donet.donation.application.port.out.GetInterestedCategoriesPort;
import com.donet.donet.global.enums.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetInterestedCategoriesAdapter implements GetInterestedCategoriesPort {
    @Override
    public List<Category> getInterestedCategories(Long userId) {
        return List.of();
    }
}
