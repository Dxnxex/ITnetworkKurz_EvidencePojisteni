package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelPojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelPojisteniEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T20:41:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class EvidenceUzivatelPojisteniMapperImpl implements EvidenceUzivatelPojisteniMapper {

    @Override
    public EvidenceUzivatelPojisteniDTO toDTO(EvidenceUzivatelPojisteniEntity source) {
        if ( source == null ) {
            return null;
        }

        EvidenceUzivatelPojisteniDTO evidenceUzivatelPojisteniDTO = new EvidenceUzivatelPojisteniDTO();

        return evidenceUzivatelPojisteniDTO;
    }

    @Override
    public EvidenceUzivatelPojisteniEntity toEntity(EvidenceUzivatelPojisteniDTO source) {
        if ( source == null ) {
            return null;
        }

        EvidenceUzivatelPojisteniEntity evidenceUzivatelPojisteniEntity = new EvidenceUzivatelPojisteniEntity();

        return evidenceUzivatelPojisteniEntity;
    }
}
