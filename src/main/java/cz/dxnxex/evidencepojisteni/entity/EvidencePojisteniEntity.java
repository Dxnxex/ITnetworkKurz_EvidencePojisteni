package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class EvidencePojisteniEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String predmet;
    private BigDecimal castka;
    private LocalDate odData;
    private LocalDate doData;


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
