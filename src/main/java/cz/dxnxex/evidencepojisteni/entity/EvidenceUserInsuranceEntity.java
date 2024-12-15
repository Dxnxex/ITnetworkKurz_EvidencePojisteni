package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EvidenceUserInsuranceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EvidenceUserEntity user;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private EvidenceInsuranceEntity insurance;

    private int value;




}

