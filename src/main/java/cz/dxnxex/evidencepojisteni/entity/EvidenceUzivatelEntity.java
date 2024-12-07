package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EvidenceUzivatelEntity {



    //region Proměnné

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // Unikátní identifikátor
    private String jmeno;          // Jméno osoby
    private String prijmeni;       // Příjmení osoby
    private String email;          // Emailová adresa
    private String telefon;        // Telefonní číslo
    private String uliceACislo;    // Ulice a číslo popisné
    private String mesto;          // Město
    private String psc;            // Poštovní směrovací číslo (PSČ)


    //endregion





}


/*


    @OneToMany(mappedBy = "uzivatel")
    private List<EvidenceUzivatelPojisteniEntity> items = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "uzivatel_pojisteni", joinColumns = @JoinColumn(name = "uzivatel_id"), inverseJoinColumns = @JoinColumn(name = "pojisteni_id"))
    private Set<EvidencePojisteniEntity> pojisteni = new HashSet<>();

    @OneToMany(mappedBy = "uzivatel", cascade = CascadeType.ALL)
    private List<EvidenceUzivatelDTO> uzivatelPojisteni = new ArrayList<>();
 */