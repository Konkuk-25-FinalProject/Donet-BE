package com.donet.donet.donation.adapter.out.persistence.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationImageRepository extends JpaRepository<DonationImageJpaEntity, Long> {
    DonationImageJpaEntity findByDonationJpaEntity(DonationJpaEntity donation);
}
