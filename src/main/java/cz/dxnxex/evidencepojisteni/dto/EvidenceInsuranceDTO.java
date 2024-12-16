package cz.dxnxex.evidencepojisteni.dto;


import cz.dxnxex.evidencepojisteni.EvidenceConfiguration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceInsuranceDTO {




    private Long id;

    @NotBlank                  (message = "Název nesmí být prázdná")
    @Size                          (min = EvidenceConfiguration.SIZE_MIN_NAME, max = EvidenceConfiguration.SIZE_MAX_NAME, message = "Název musí mít délku mezi " + EvidenceConfiguration.SIZE_MIN_NAME + "  a " + EvidenceConfiguration.SIZE_MAX_NAME + " znaky")
    @Pattern                    (regexp = EvidenceConfiguration.CZECH_ALPHABET_REGEX, message = EvidenceConfiguration.CZECH_ALPHABET_REGEX_MESSAGE)
    private String name;

    private String description;



}
