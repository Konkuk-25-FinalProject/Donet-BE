package com.donet.donet.donation.adapter.out.persistence.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerJpaEntity, Long> {
}
