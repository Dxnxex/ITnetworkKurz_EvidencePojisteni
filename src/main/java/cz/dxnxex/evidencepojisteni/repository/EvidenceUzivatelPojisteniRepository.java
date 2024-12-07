package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelPojisteniEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceUzivatelPojisteniRepository extends JpaRepository<EvidenceUzivatelPojisteniEntity, Long> { }
