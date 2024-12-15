package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceInsuranceRepository extends JpaRepository<EvidenceInsuranceEntity, Long> {}
