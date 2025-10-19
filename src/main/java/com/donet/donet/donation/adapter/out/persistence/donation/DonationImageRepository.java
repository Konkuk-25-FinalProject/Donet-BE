package com.donet.donet.donation.adapter.out.persistence.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationImageRepository extends JpaRepository<DonationImageJpaEntity, Long> {
    List<DonationImageJpaEntity> findByDonationJpaEntity(DonationJpaEntity donation);
}
