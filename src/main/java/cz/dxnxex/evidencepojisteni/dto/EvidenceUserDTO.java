package cz.dxnxex.evidencepojisteni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EvidenceUserDTO {


        private Long id;               // Unikátní identifikátor

        @NotBlank(message = "Jméno nesmí být prázdné")
        @Size(min = 2, max = 50, message = "Jméno musí mít délku mezi 2 a 50 znaky")
        @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž]+$", message = "Jméno může obsahovat pouze písmena bez čísel a speciálních znaků")
        private String name;  // Jméno osoby

        @NotBlank(message = "Příjmení nesmí být prázdné")
        @Size(min = 2, max = 50, message = "Příjmení musí mít délku mezi 2 a 50 znaky")
        @Pattern(regexp = "^[a-zA-Zá-žÁ-Ž]+$", message = "Příjmení může obsahovat pouze písmena bez čísel a speciálních znaků")
        private String surname;  // Příjmení osoby

        @NotBlank(message = "Email nesmí být prázdný")
        @Email(message = "Email musí mít správný formát")
        private String email;  // Emailová adresa

        @NotBlank(message = "Telefonní číslo nesmí být prázdné")
        @Pattern(regexp = "^\\d{3} \\d{3} \\d{3}$", message = "Telefonní číslo musí být ve formátu 123 456 789")
        private String telephone;  // Telefonní číslo

        @NotBlank(message = "Ulice a číslo popisné nesmí být prázdné")
        @Size(max = 100, message = "Ulice a číslo popisné nesmí být delší než 100 znaků")
        private String streetAndNumber;  // Ulice a číslo popisné

        @NotBlank(message = "Město nesmí být prázdné")
        @Size(max = 50, message = "Název města nesmí být delší než 50 znaků")
        private String city;  // Město

        @NotBlank(message = "PSČ nesmí být prázdné")
        @Pattern(regexp = "^\\d{3} \\d{2}$", message = "PSČ musí být ve formátu 123 45")
        private String postalCode;  // Poštovní směrovací číslo (PSČ)

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

        //endregion


}
