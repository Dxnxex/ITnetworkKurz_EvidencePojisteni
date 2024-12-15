package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class EvidenceInsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
    private List<EvidenceUserInsuranceEntity> uzivatelovaPojisteni = new ArrayList<>();;


}
