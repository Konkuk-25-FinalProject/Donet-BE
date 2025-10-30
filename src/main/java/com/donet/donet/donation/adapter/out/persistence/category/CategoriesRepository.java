package com.donet.donet.donation.adapter.out.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoryJpaEntity, Long> {
    @Query(
            value = "SELECT * FROM category c WHERE c.id IN (SELECT ic.category_id FROM interested_category ic WHERE ic.user_id = :userId)",
            nativeQuery = true
    )
    List<CategoryJpaEntity> findInterestedCategories(@Param("userId") Long userId);

    @Query(
            value = "SELECT * FROM category c WHERE c.name IN :names",
            nativeQuery = true
    )
    List<CategoryJpaEntity> findCategoriesByName(@Param("names") List<String> names);

    @Query(
            value = "SELECT COUNT(DISTINCT name) = :#{#names.size()} FROM category WHERE name IN (:names)",
            nativeQuery = true
    )
    boolean existsByName(@Param("names") List<String> names);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO DonationCategory (donationId, categoryId) " +
                    "SELECT :donationId, c.id FROM Category c WHERE c.name IN (:categoryNames)",
            nativeQuery = true
    )
    void saveDonationCategory(@Param("donationId") Long donationId, @Param("categoryNames") List<String> categoryNames);
}
