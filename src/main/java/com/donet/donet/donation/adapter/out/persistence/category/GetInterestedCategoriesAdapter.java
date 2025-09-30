package com.donet.donet.donation.adapter.out.persistence;

import com.donet.donet.donation.application.port.out.GetInterestedCategoriesPort;
import com.donet.donet.global.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetInterestedCategoriesAdapter implements GetInterestedCategoriesPort {
    private final

    @Override
    public List<Category> findInterestedCategories(Long userId) {
        return List.of();
    }
}
