package cz.dxnxex.evidencepojisteni.mapper;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidencePojisteniMapper {

    EvidencePojisteniDTO toDTO(EvidencePojisteniEntity source);
    EvidencePojisteniEntity toEntity(EvidencePojisteniDTO source);

}
