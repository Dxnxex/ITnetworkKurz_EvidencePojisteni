package cz.dxnxex.evidencepojisteni.entity;

import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //region Gettery & Settery



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUliceACislo() {
        return uliceACislo;
    }

    public void setUliceACislo(String uliceACislo) {
        this.uliceACislo = uliceACislo;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }



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