package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EvidencePojisteniEntity {

    @OneToMany(mappedBy = "pojisteni")
    private List<EvidenceUzivatelPojisteniEntity> items = new ArrayList<>();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String predmet;

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



}
