package cz.dxnxex.evidencepojisteni.mapper;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojistenychDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojistenychEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvidencePojistenychMapper {

    EvidencePojistenychDTO toDTO(EvidencePojistenychEntity source);
    EvidencePojistenychEntity toEntity(EvidencePojistenychDTO source);

}
