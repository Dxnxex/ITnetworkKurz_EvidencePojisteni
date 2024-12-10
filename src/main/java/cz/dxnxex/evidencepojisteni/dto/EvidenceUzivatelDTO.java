package cz.dxnxex.evidencepojisteni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EvidenceUzivatelDTO {


        private Long id;               // Unikátní identifikátor

        @NotBlank(message = "Jméno nesmí být prázdné")
        @Size(min = 2, max = 50, message = "Jméno musí mít délku mezi 2 a 50 znaky")
        @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž]+$", message = "Jméno může obsahovat pouze písmena bez čísel a speciálních znaků")
        private String jmeno;  // Jméno osoby

        @NotBlank(message = "Příjmení nesmí být prázdné")
        @Size(min = 2, max = 50, message = "Příjmení musí mít délku mezi 2 a 50 znaky")
        @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž]+$", message = "Příjmení může obsahovat pouze písmena bez čísel a speciálních znaků")
        private String prijmeni;  // Příjmení osoby

        @NotBlank(message = "Email nesmí být prázdný")
        @Email(message = "Email musí mít správný formát")
        private String email;  // Emailová adresa

        @NotBlank(message = "Telefonní číslo nesmí být prázdné")
        @Pattern(regexp = "^\\d{3} \\d{3} \\d{3}$", message = "Telefonní číslo musí být ve formátu 123 456 789")
        private String telefon;  // Telefonní číslo

        @NotBlank(message = "Ulice a číslo popisné nesmí být prázdné")
        @Size(max = 100, message = "Ulice a číslo popisné nesmí být delší než 100 znaků")
        private String uliceACislo;  // Ulice a číslo popisné

        @NotBlank(message = "Město nesmí být prázdné")
        @Size(max = 50, message = "Název města nesmí být delší než 50 znaků")
        private String mesto;  // Město

        @NotBlank(message = "PSČ nesmí být prázdné")
        @Pattern(regexp = "^\\d{3} \\d{2}$", message = "PSČ musí být ve formátu 123 45")
        private String psc;  // Poštovní směrovací číslo (PSČ)

        //region GETTERS & SETTERS

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
