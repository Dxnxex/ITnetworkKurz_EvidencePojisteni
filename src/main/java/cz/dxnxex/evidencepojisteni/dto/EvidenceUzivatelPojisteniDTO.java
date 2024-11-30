package cz.dxnxex.evidencepojisteni.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EvidenceUzivatelPojisteniDTO {


    private Long id;               // Unikátní identifikátor
    private BigDecimal castka;     // Částka pojištění
    private LocalDate odData;      // Počáteční datum pojištění
    private LocalDate doData;      // Konečné datum pojištění


        public Long getId()                         { return id; }
        public void setId(Long id)                  { this.id = id; }

        public BigDecimal getCastka()               { return castka; }
        public void setCastka(BigDecimal castka)    { this.castka = castka; }

        public LocalDate getOdData()                { return odData; }
        public void setOdData(LocalDate odData)     { this.odData = odData; }

        public LocalDate getDoData()                { return doData; }
        public void setDoData(LocalDate doData)     { this.doData = doData; }



}
