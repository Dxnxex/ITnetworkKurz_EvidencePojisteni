package cz.dxnxex.evidencepojisteni.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class EvidenceUzivatelPojisteniDTO {

    // Gettery a settery
    private Long uzivatelId;
    private Long pojisteniId;
    private BigDecimal castka;
    private LocalDate odData;
    private LocalDate doData;

}
