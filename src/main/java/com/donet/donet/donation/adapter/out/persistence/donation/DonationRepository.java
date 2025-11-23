package com.donet.donet.donation.adapter.out.persistence.donation;

import com.donet.donet.user.adapter.out.persistence.UserJpaEntity;
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
                    "WHERE dc.category_id IN (:categoryIds) AND d.end_date >= CURRENT_DATE AND d.status = 'ACTIVE' " +
                    "GROUP BY d.id " +
                    "HAVING COUNT(DISTINCT dc.category_id) = :size " +
                    "LIMIT :donationSize",
            nativeQuery = true)
    List<DonationJpaEntity> findDonationWithAllCategories(@Param("categoryIds") List<Long> categoryIds, @Param("size") int size, @Param("donationSize") Integer donationSize);

    @Query(value = "SELECT * FROM donation LIMIT 1", nativeQuery = true)
    DonationJpaEntity findAnyDonation();

    @Query(value = "SELECT * FROM donation d WHERE d.end_date >= CURRENT_DATE AND d.status = 'ACTIVE' ORDER BY d.views DESC LIMIT :size", nativeQuery = true)
    List<DonationJpaEntity> findPopularDonations(@Param("size") Integer size);

    @Query(value =
            "SELECT * FROM donation d " +
                    "WHERE d.target_amount != d.current_amount AND d.end_date >= CURRENT_DATE AND d.status = 'ACTIVE'" +
                    "ORDER BY (d.target_amount - d.current_amount) ASC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    DonationJpaEntity findImminentDonation();

    @Query(
            value =
                    "SELECT d.* FROM donation d " +
                            "JOIN donation_category dc ON d.id = dc.donation_id " +
                            "WHERE dc.category_id IN (:categoryIds) AND d.end_date >= CURRENT_DATE AND d.status = 'ACTIVE' " +
                            "GROUP BY d.id",
            countQuery =
                    "SELECT COUNT(DISTINCT d.id) FROM donation d " +
                            "JOIN donation_category dc ON d.id = dc.donation_id " +
                            "WHERE dc.category_id IN (:categoryIds)",
            nativeQuery = true
    )
    Page<DonationJpaEntity> findDonationWithCategoriesAndPagination(
            @Param("categoryIds") List<Long> categoryIds,
            Pageable pageable
    );

    Optional<DonationJpaEntity> findDonationById(Long id);

    @Query(value =
            "SELECT dr.donation_id FROM donation_record dr " +
                    "WHERE dr.users_id = :userId ",
            nativeQuery = true)
    List<Long> getDonationIdsUserDonated(@Param("userId") Long userId);

    @Query(
            value = "SELECT * FROM donation d WHERE d.end_date >= CURRENT_DATE AND d.status = 'ACTIVE' LIMIT :size",
            nativeQuery = true
    )
    List<DonationJpaEntity> findDonationsLimit(@Param("size") Integer size);

    @Query(
            value = """
                    SELECT 
                        record.donation_id AS donationId,
                        d.title AS title,
                        (SELECT di.image_url FROM donation_image di 
                         WHERE di.donation_id = record.donation_id 
                         ORDER BY di.id DESC LIMIT 1) AS imageUrl,
                        record.donation_amount AS donatedAmount
                    FROM donation_record record
                    JOIN donation d ON record.donation_id = d.id
                    WHERE record.user_id = :userId
                    ORDER BY record.id DESC""",
            countQuery = """
                    SELECT COUNT(*)
                    FROM donation_record record
                    WHERE record.user_id = :userId""",
            nativeQuery = true)
    Page<JoinedDonationProjection> findJoinedDonations(@Param("userId") Long userId, Pageable pageable);

    List<DonationJpaEntity> findAllByUserJpaEntityOrderByIdDesc(UserJpaEntity userJpaEntity, Pageable pageable);
}
