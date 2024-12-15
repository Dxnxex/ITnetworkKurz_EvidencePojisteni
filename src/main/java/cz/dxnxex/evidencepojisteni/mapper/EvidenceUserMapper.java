package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUserDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidenceUserMapper {

    //Přepravky
    EvidenceUserDTO toDTO(EvidenceUserEntity source);
    EvidenceUserEntity toEntity(EvidenceUserDTO source);

}
