package cz.dxnxex.evidencepojisteni.mapper;



import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelPojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelPojisteniEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidenceUzivatelPojisteniMapper {

    EvidenceUzivatelPojisteniDTO toDTO(EvidenceUzivatelPojisteniEntity source);
    EvidenceUzivatelPojisteniEntity toEntity(EvidenceUzivatelPojisteniDTO source);

}
