package cz.dxnxex.evidencepojisteni.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class EvidencePojisteniDTO {

    //TESTOVÁNÍ #5 - Dxnxex

    private Long id;               // Unikátní identifikátor
    private String predmet;        // Předmět pojištění
    private String popis;           // Text pojištění

    private Set<Long> uzivatelIDs;


}
