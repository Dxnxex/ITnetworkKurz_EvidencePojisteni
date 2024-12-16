package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceInsuranceDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidenceInsuranceMapper {

    EvidenceInsuranceDTO toDTO(EvidenceInsuranceEntity source);
    EvidenceInsuranceEntity toEntity(EvidenceInsuranceDTO source);

}
