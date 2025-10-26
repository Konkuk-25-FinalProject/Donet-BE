package com.donet.donet.donation.adapter.out.persistence.donationRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRecordRepository extends JpaRepository<DonationRecordJpaEntity, Long> {
}
