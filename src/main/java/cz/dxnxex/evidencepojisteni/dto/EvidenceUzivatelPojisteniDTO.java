package cz.dxnxex.evidencepojisteni.dto;


import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;

public class EvidenceUzivatelPojisteniDTO {


    private Long id;                                // Unikátní identifikátor
    private EvidenceUzivatelEntity uzivatel;
    private EvidencePojisteniEntity pojisteni;
    private int castka;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvidenceUzivatelEntity getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(EvidenceUzivatelEntity uzivatel) {
        this.uzivatel = uzivatel;
    }

    public EvidencePojisteniEntity getPojisteni() {
        return pojisteni;
    }

    public void setPojisteni(EvidencePojisteniEntity pojisteni) {
        this.pojisteni = pojisteni;
    }

    public int getCastka() {
        return castka;
    }

    public void setCastka(int castka) {
        this.castka = castka;
    }
}
