package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidencePojisteniRepository extends JpaRepository<EvidencePojisteniEntity, Long> {




}
