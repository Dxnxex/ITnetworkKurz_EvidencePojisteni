package cz.dxnxex.evidencepojisteni.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
public class EvidencePojisteniDTO {


    private Long id;               // Unikátní identifikátor
    private String predmet;        // Předmět pojištění
    private BigDecimal castka;     // Částka pojištění
    private LocalDate odData;      // Počáteční datum pojištění
    private LocalDate doData;      // Konečné datum pojištění


        public Long getId()                         { return id; }
        public void setId(Long id)                  { this.id = id; }

        public String getPredmet()                  { return predmet; }
        public void setPredmet(String predmet)      { this.predmet = predmet; }

        public BigDecimal getCastka()               { return castka; }
        public void setCastka(BigDecimal castka)    { this.castka = castka; }

        public LocalDate getOdData()                { return odData; }
        public void setOdData(LocalDate odData)     { this.odData = odData; }

        public LocalDate getDoData()                { return doData; }
        public void setDoData(LocalDate doData)     { this.doData = doData; }



}
