package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

@Entity
public class EvidenceUserInsuranceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // Unikátní identifikátor

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EvidenceUserEntity user;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private EvidenceInsuranceEntity insurance;

    private int value;


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

