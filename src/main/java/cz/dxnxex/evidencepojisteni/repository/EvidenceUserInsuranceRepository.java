package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidenceUserInsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceUserInsuranceRepository extends JpaRepository<EvidenceUserInsuranceEntity, Long> {}
