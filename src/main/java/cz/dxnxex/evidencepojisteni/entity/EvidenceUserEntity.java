package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
public class EvidenceUserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String streetAndNumber;
    private String city;
    private String postalCode;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EvidenceUserInsuranceEntity> userInsurances = new ArrayList<>();;


}


