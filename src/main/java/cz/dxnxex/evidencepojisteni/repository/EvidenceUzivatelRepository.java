package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceUzivatelRepository extends JpaRepository<EvidenceUzivatelEntity, Long> { }
