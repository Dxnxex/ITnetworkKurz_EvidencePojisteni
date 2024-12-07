package cz.dxnxex.evidencepojisteni.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
public class EvidencePojisteniDTO {




    private Long id;               // Unikátní identifikátor
    private String predmet;        // Předmět pojištění

        public Long getId()                         { return id; }
        public void setId(Long id)                  { this.id = id; }

        public String getPredmet()                  { return predmet; }
        public void setPredmet(String predmet)      { this.predmet = predmet; }




}
