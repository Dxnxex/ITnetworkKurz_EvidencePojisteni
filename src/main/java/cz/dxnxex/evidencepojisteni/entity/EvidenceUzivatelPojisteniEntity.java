package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EvidenceUzivatelPojisteniEntity {

    @ManyToOne
    @JoinColumn(name = "uzivatel_id", nullable = false)
    private EvidenceUzivatelEntity uzivatel;

    @ManyToOne
    @JoinColumn(name = "pojisteni_id", nullable = false)
    private EvidencePojisteniEntity pojisteni;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal castka;
    private LocalDate odData;
    private LocalDate doData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCastka() {
        return castka;
    }

    public void setCastka(BigDecimal castka) {
        this.castka = castka;
    }

    public LocalDate getOdData() {
        return odData;
    }

    public void setOdData(LocalDate odData) {
        this.odData = odData;
    }

    public LocalDate getDoData() {
        return doData;
    }

    public void setDoData(LocalDate doData) {
        this.doData = doData;
    }


}
