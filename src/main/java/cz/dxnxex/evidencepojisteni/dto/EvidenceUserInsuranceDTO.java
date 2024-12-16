package cz.dxnxex.evidencepojisteni.dto;


import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceUserInsuranceDTO {


    private Long id;
    private EvidenceUserEntity user;
    private EvidenceInsuranceEntity insurance;
    private int value;


}
