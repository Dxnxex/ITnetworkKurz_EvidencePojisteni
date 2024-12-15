package cz.dxnxex.evidencepojisteni.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EvidenceInsuranceDTO {

    private Long id;                    // Unikátní identifikátor

    @NotBlank(message = "Název nesmí být prázdná")
    @Size(min = 1, max = 50, message = "Název musí mít délku mezi 2 a 50 znaky")
    @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Název může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků")
    private String name;                // Název či předmět pojištění

    private String description;          // Text pojištění

    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Název nesmí být prázdná") @Size(min = 1, max = 50, message = "Název musí mít délku mezi 2 a 50 znaky") @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Název může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Název nesmí být prázdná") @Size(min = 1, max = 50, message = "Název musí mít délku mezi 2 a 50 znaky") @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Název může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //endregion

}
