package cz.dxnxex.evidencepojisteni.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvidenceInsuranceDTO {

    private Long id;                    // Unikátní identifikátor

    @NotBlank(message = "Název nesmí být prázdná")
    @Size(min = 1, max = 50, message = "Název musí mít délku mezi 2 a 50 znaky")
    @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Název může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků")
    private String name;                // Název či předmět pojištění

    private String description;          // Text pojištění


}
