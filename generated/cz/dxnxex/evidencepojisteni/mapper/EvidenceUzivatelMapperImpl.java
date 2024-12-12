package cz.dxnxex.evidencepojisteni.mapper;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T20:41:53+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class EvidenceUzivatelMapperImpl implements EvidenceUzivatelMapper {

    @Override
    public EvidenceUzivatelDTO toDTO(EvidenceUzivatelEntity source) {
        if ( source == null ) {
            return null;
        }

        EvidenceUzivatelDTO evidenceUzivatelDTO = new EvidenceUzivatelDTO();

        evidenceUzivatelDTO.setId( source.getId() );
        evidenceUzivatelDTO.setJmeno( source.getJmeno() );
        evidenceUzivatelDTO.setPrijmeni( source.getPrijmeni() );
        evidenceUzivatelDTO.setEmail( source.getEmail() );
        evidenceUzivatelDTO.setTelefon( source.getTelefon() );
        evidenceUzivatelDTO.setUliceACislo( source.getUliceACislo() );
        evidenceUzivatelDTO.setMesto( source.getMesto() );
        evidenceUzivatelDTO.setPsc( source.getPsc() );

        return evidenceUzivatelDTO;
    }

    @Override
    public EvidenceUzivatelEntity toEntity(EvidenceUzivatelDTO source) {
        if ( source == null ) {
            return null;
        }

        EvidenceUzivatelEntity evidenceUzivatelEntity = new EvidenceUzivatelEntity();

        evidenceUzivatelEntity.setId( source.getId() );
        evidenceUzivatelEntity.setJmeno( source.getJmeno() );
        evidenceUzivatelEntity.setPrijmeni( source.getPrijmeni() );
        evidenceUzivatelEntity.setEmail( source.getEmail() );
        evidenceUzivatelEntity.setTelefon( source.getTelefon() );
        evidenceUzivatelEntity.setUliceACislo( source.getUliceACislo() );
        evidenceUzivatelEntity.setMesto( source.getMesto() );
        evidenceUzivatelEntity.setPsc( source.getPsc() );

        return evidenceUzivatelEntity;
    }

    @Override
    public void updateEvidenceDTO(EvidenceUzivatelDTO source, EvidenceUzivatelDTO target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setJmeno( source.getJmeno() );
        target.setPrijmeni( source.getPrijmeni() );
        target.setEmail( source.getEmail() );
        target.setTelefon( source.getTelefon() );
        target.setUliceACislo( source.getUliceACislo() );
        target.setMesto( source.getMesto() );
        target.setPsc( source.getPsc() );
    }

    @Override
    public void updateEvidenceEntity(EvidenceUzivatelDTO source, EvidenceUzivatelEntity target) {
        if ( source == null ) {
            return;
        }

        target.setId( source.getId() );
        target.setJmeno( source.getJmeno() );
        target.setPrijmeni( source.getPrijmeni() );
        target.setEmail( source.getEmail() );
        target.setTelefon( source.getTelefon() );
        target.setUliceACislo( source.getUliceACislo() );
        target.setMesto( source.getMesto() );
        target.setPsc( source.getPsc() );
    }
}
