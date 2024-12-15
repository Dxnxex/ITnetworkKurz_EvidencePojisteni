package cz.dxnxex.evidencepojisteni.dto;


import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceUserInsuranceDTO {


    private Long id;                                    // Unikátní identifikátor
    private EvidenceUserEntity user;                    // Entita uživatele
    private EvidenceInsuranceEntity insurance;          // Entita pojištění
    private int value;                                 // Částka pojištění

    //region GETTERS & SETTERS


    //endregion


}
