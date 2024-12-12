package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidenceUzivatelMapper {

    //Přepravky
    EvidenceUzivatelDTO toDTO(EvidenceUzivatelEntity source);
    EvidenceUzivatelEntity toEntity(EvidenceUzivatelDTO source);

}
