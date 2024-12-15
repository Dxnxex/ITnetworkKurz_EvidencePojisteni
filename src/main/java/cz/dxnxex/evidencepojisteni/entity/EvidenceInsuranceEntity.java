package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EvidenceInsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
    private List<EvidenceUserInsuranceEntity> uzivatelovaPojisteni = new ArrayList<>();;


    //region GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EvidenceUserInsuranceEntity> getUzivatelovaPojisteni() {
        return uzivatelovaPojisteni;
    }

    public void setUzivatelovaPojisteni(List<EvidenceUserInsuranceEntity> uzivatelovaPojisteni) {
        this.uzivatelovaPojisteni = uzivatelovaPojisteni;
    }


    //endregion

}
