package cz.dxnxex.evidencepojisteni.dto;


import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;

public class EvidenceUserInsuranceDTO {


    private Long id;                                    // Unikátní identifikátor
    private EvidenceUserEntity user;                    // Entita uživatele
    private EvidenceInsuranceEntity insurance;          // Entita pojištění
    private int value;                                 // Částka pojištění

    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvidenceUserEntity getUser() {
        return user;
    }

    public void setUser(EvidenceUserEntity user) {
        this.user = user;
    }

    public EvidenceInsuranceEntity getInsurance() {
        return insurance;
    }

    public void setInsurance(EvidenceInsuranceEntity insurance) {
        this.insurance = insurance;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    //endregion


}
