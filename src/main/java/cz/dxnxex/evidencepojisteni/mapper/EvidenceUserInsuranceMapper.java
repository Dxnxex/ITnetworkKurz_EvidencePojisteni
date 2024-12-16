package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUserInsuranceDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserInsuranceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidenceUserInsuranceMapper {

    EvidenceUserInsuranceDTO toDTO(EvidenceUserInsuranceEntity source);
    EvidenceUserInsuranceEntity toEntity(EvidenceUserInsuranceDTO source);

}
