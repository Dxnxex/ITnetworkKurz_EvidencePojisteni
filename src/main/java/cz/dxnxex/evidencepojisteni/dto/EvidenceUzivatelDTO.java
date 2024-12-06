package cz.dxnxex.evidencepojisteni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class EvidenceUzivatelDTO {


        //region Proměnné

        private Long id;

        @NotBlank(message = "Vyplňte jméno")
        @Size(min = 2, message = "Jméno musí mít alespoň 2 znaky.")
        private String jmeno;

        @NotBlank(message = "Vyplňte příjmení")
        @Size(min = 2, message = "Příjmení musí mít alespoň 2 znaky.")
        private String prijmeni;

        @NotBlank(message = "Vyplňte email")
        @Email(message = "Zadejte platnou emailovou adresu.")
        private String email;

        @Pattern(regexp = "^\\d{3} \\d{3} \\d{3}$", message = "Telefonní číslo musí obsahovat 9 číslic ve formátu ### ### ###")
        private String telefon;

        @NotBlank(message = "Vyplňte ulici a číslo popisné")
        private String uliceACislo;

        @NotBlank(message = "Vyplňte město")
        private String mesto;

        @Pattern(regexp = "\\d{3} \\d{2}", message = "PSČ musí obsahovat 5 číslic s mezerou za prvním trojčíslým.")
        private String psc;

        private Set<Long> pojisteniIDs;

        //endregion


}
