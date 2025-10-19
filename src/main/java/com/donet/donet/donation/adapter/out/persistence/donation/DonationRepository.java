package com.donet.donet.donation.adapter.out.persistence.donation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<DonationJpaEntity, Long> {
    @Query(value =
            "SELECT d.* FROM donation d " +
                    "JOIN donation_category dc ON d.id = dc.donation_id " +
                    "WHERE dc.category_id IN (:categoryIds) " +
                    "GROUP BY d.id " +
                    "HAVING COUNT(DISTINCT dc.category_id) = :size " +
                    "LIMIT 1",
            nativeQuery = true)
    Optional<DonationJpaEntity> findDonationWithAllCategories(@Param("categoryIds") List<Long> categoryIds, @Param("size") int size);

    @Query(value = "SELECT * FROM donation LIMIT 1", nativeQuery = true)
    DonationJpaEntity findAnyDonation();

    @Query(value = "SELECT * FROM donation d ORDER BY d.views DESC LIMIT 1", nativeQuery = true)
    DonationJpaEntity findPopularDonation();

    @Query(value =
            "SELECT * FROM donation d " +
                    "WHERE d.targetAmount != d.currentAmount " +
                    "ORDER BY (d.targetAmount - d.currentAmount) ASC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    DonationJpaEntity findImminentDonation();

    @Query(value =
            "SELECT d.* FROM donation d " +
                    "JOIN donation_category dc ON d.id = dc.donation_id " +
                    "WHERE dc.category_id IN (:categoryIds) " +
                    "GROUP BY d.id " +
                    "HAVING COUNT(DISTINCT dc.category_id) = :size ",
            nativeQuery = true)
    Page<DonationJpaEntity> findDonationWithCategoriesAndPagination(@Param("categoryIds") List<Long> categoryIds, @Param("size") int size, Pageable pageable);

    Optional<DonationJpaEntity> findDonationById(Long id);
}
