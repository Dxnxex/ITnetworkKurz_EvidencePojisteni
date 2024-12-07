package cz.dxnxex.evidencepojisteni.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceUzivatelDTO {


        //region Proměnné

        private Long id;               // Unikátní identifikátor
        private String jmeno;          // Jméno osoby
        private String prijmeni;       // Příjmení osoby
        private String email;          // Emailová adresa
        private String telefon;        // Telefonní číslo
        private String uliceACislo;    // Ulice a číslo popisné
        private String mesto;          // Město
        private String psc;            // Poštovní směrovací číslo (PSČ)

        //endregion

        //TEST 2


}
