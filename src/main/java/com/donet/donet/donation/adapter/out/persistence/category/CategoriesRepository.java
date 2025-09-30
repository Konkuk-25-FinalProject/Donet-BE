package com.donet.donet.donation.adapter.out.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoryJpaEntity, Long> {
    @Query(
            value = "SELECT * FROM category c WHERE c.id IN (SELECT ic.category_id FROM interested_category ic WHERE ic.user_id = :userId)",
            nativeQuery = true
    )
    List<CategoryJpaEntity> findInterestedCategories(@Param("userId") Long userId);
}
