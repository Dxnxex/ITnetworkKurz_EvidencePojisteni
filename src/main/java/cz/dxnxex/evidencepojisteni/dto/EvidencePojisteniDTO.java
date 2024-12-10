package cz.dxnxex.evidencepojisteni.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EvidencePojisteniDTO {

    private Long id;               // Unikátní identifikátor

    @NotBlank(message = "Název nesmí být prázdná")
    @Size(min = 1, max = 50, message = "Název musí mít délku mezi 2 a 50 znaky")
    @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž ]+$", message = "Název může obsahovat pouze písmena a mezery, bez čísel a speciálních znaků")
    private String predmet;        // Název či předmět pojištění

    private String popis;          // Text pojištění

    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    //endregion


}
