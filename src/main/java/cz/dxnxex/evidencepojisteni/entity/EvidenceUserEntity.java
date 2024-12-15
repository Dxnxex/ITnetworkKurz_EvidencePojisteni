package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EvidenceUserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // Unikátní identifikátor

    private String name;                // Jméno osoby
    private String surname;             // Příjmení osoby
    private String email;               // Emailová adresa
    private String telephone;           // Telefonní číslo
    private String streetAndNumber;     // Ulice a číslo popisné
    private String city;                // Město
    private String postalCode;          // Poštovní směrovací číslo (PSČ)


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EvidenceUserInsuranceEntity> userInsurances = new ArrayList<>();;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<EvidenceUserInsuranceEntity> getUserInsurances() {
        return userInsurances;
    }

    public void setUserInsurances(List<EvidenceUserInsuranceEntity> userInsurances) {
        this.userInsurances = userInsurances;
    }

    //endregion


}


