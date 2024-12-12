package cz.dxnxex.evidencepojisteni.repository;

import cz.dxnxex.evidencepojisteni.entity.EvidenceAccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EvidenceAccountRepository extends CrudRepository<EvidenceAccountEntity, Long> {

    Optional<EvidenceAccountEntity> findByEmail(String email);

}
