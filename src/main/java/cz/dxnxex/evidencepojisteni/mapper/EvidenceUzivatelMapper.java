package cz.dxnxex.evidencepojisteni.mapper;


import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EvidenceUzivatelMapper {

    //Přepravka
    EvidenceUzivatelDTO toDTO(EvidenceUzivatelEntity source);
    EvidenceUzivatelEntity toEntity(EvidenceUzivatelDTO source);

    //Editace
    void updateEvidenceDTO(EvidenceUzivatelDTO source, @MappingTarget EvidenceUzivatelDTO target);
    void updateEvidenceEntity(EvidenceUzivatelDTO source, @MappingTarget EvidenceUzivatelEntity target);

}
