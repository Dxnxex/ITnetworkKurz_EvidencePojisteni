package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EvidencePojisteniEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String predmet;
    private String popis;


    @ManyToMany(mappedBy = "pojisteni")
    private List<EvidenceUzivatelEntity> uzivatele = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public List<EvidenceUzivatelEntity> getUzivatele() {
        return uzivatele;
    }

    public void setUzivatele(List<EvidenceUzivatelEntity> uzivatele) {
        this.uzivatele = uzivatele;
    }
}
