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
                    "WHERE dc.category_id IN (:categoryIds) " +
                    "GROUP BY d.id " +
                    "HAVING COUNT(DISTINCT dc.category_id) = :size " +
                    "LIMIT :donationSize",
            nativeQuery = true)
    List<DonationJpaEntity> findDonationWithAllCategories(@Param("categoryIds") List<Long> categoryIds, @Param("size") int size, @Param("donationSize") Integer donationSize);

    @Query(value = "SELECT * FROM donation LIMIT 1", nativeQuery = true)
    DonationJpaEntity findAnyDonation();

    @Query(value = "SELECT * FROM donation d ORDER BY d.views DESC LIMIT :size", nativeQuery = true)
    List<DonationJpaEntity> findPopularDonations(@Param("size") Integer size);

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

    @Query(value =
            "SELECT dr.donation_id FROM donation_record dr " +
                    "WHERE dr.user_id = :userId ",
            nativeQuery = true)
    List<Long> getDonationIdsUserDonated(@Param("userId") Long userId);

    @Query(
            value = "SELECT * FROM donation LIMIT :size",
            nativeQuery = true
    )
    List<DonationJpaEntity> findDonationsLimit(@Param("size") Integer size);

    @Query("""
         select record.donationJpaEntity.title AS title,
             (select di from DonationImageJpaEntity di where di.donationJpaEntity = record.donationJpaEntity order by di.id desc limit 1) AS imageUrl,
             record.donationAmount AS donatedAmount
             from DonationRecordJpaEntity record 
             where record.userJpaEntity = :user
                 order by record.id desc
    """)
    List<JoinedDonationProjection> findJoinedDonations(@Param("user") UserJpaEntity userJpaEntity, Pageable pageable);

    List<DonationJpaEntity> findAllByUserJapEntityOrderByIdDesc(UserJpaEntity userJpaEntity, Pageable pageable);
}
