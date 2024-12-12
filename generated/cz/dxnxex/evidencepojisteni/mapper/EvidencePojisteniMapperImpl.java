package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T20:41:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class EvidencePojisteniMapperImpl implements EvidencePojisteniMapper {

    @Override
    public EvidencePojisteniDTO toDTO(EvidencePojisteniEntity source) {
        if ( source == null ) {
            return null;
        }

        EvidencePojisteniDTO evidencePojisteniDTO = new EvidencePojisteniDTO();

        return evidencePojisteniDTO;
    }

    @Override
    public EvidencePojisteniEntity toEntity(EvidencePojisteniDTO source) {
        if ( source == null ) {
            return null;
        }

        EvidencePojisteniEntity evidencePojisteniEntity = new EvidencePojisteniEntity();

        return evidencePojisteniEntity;
    }
}
