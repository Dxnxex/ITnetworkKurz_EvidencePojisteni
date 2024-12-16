package cz.dxnxex.evidencepojisteni.dto;

import cz.dxnxex.evidencepojisteni.EvidenceConfiguration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceUserDTO {

        private Long id;

        @NotBlank                              (message = "Jméno nesmí být prázdné")
        @Size                                       (min = EvidenceConfiguration.SIZE_MIN_NAME, max = EvidenceConfiguration.SIZE_MAX_NAME, message = "Jméno musí mít délku mezi " + EvidenceConfiguration.SIZE_MIN_NAME + "  a " + EvidenceConfiguration.SIZE_MAX_NAME + " znaky")
        @Pattern                                 (regexp = EvidenceConfiguration.CZECH_ALPHABET_REGEX, message = EvidenceConfiguration.CZECH_ALPHABET_REGEX_MESSAGE)
        private String name;

        @NotBlank                              (message = "Příjmení nesmí být prázdné")
        @Size                                      (min = EvidenceConfiguration.SIZE_MIN_NAME, max = EvidenceConfiguration.SIZE_MAX_NAME, message = "Příjmení musí mít délku mezi " + EvidenceConfiguration.SIZE_MIN_NAME + "  a " + EvidenceConfiguration.SIZE_MAX_NAME + " znaky")
        @Pattern                                (regexp = EvidenceConfiguration.CZECH_ALPHABET_REGEX, message = EvidenceConfiguration.CZECH_ALPHABET_REGEX_MESSAGE)
        private String surname;

        @NotBlank                             (message = "Email nesmí být prázdný")
        @Email                                  (message = "Email musí mít správný formát")
        private String email;

        @NotBlank                             (message = "Telefonní číslo nesmí být prázdné")
        @Pattern                                (regexp = "^\\d{3} \\d{3} \\d{3}$", message = "Telefonní číslo musí být ve formátu 123 456 789")
        private String telephone;

        @NotBlank                              (message = "Ulice a číslo popisné nesmí být prázdné")
        @Size                                       (max = 100, message = "Ulice a číslo popisné nesmí být delší než 100 znaků")
        private String streetAndNumber;

        @NotBlank                               (message = "Město nesmí být prázdné")
        @Size                                        (min = EvidenceConfiguration.SIZE_MIN_NAME, max = EvidenceConfiguration.SIZE_MAX_NAME, message = "Název města musí mít délku mezi " + EvidenceConfiguration.SIZE_MIN_NAME + "  a " + EvidenceConfiguration.SIZE_MAX_NAME + " znaky")
        private String city;

        @NotBlank                               (message = "PSČ nesmí být prázdné")
        @Pattern                                  (regexp = "^\\d{3} \\d{2}$", message = "PSČ musí být ve formátu 123 45")
        private String postalCode;

}
