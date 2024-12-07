package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EvidencePojisteniEntity {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String predmet;
    private String popis;

}

/*

    @OneToMany(mappedBy = "pojisteni")
    private List<EvidenceUzivatelPojisteniEntity> items = new ArrayList<>();
 */