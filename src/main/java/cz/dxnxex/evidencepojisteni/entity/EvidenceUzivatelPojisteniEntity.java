package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

@Entity
public class EvidenceUzivatelPojisteniEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // Unikátní identifikátor

    @ManyToOne
    @JoinColumn(name = "uzivatel_id")
    private EvidenceUzivatelEntity uzivatel;

    @ManyToOne
    @JoinColumn(name = "pojisteni_id")
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

