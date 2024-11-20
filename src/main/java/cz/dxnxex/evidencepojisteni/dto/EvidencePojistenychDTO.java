package cz.dxnxex.evidencepojisteni.dto;

public class EvidencePojistenychDTO {


        //region Proměnné

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

                public Long     getId()                                 {return id;}
                public void     setId(Long id)                          {this.id = id;}

                public String   getJmeno()                              { return jmeno; }
                public void     setJmeno(String jmeno)                  { this.jmeno = jmeno; }

                public String   getPrijmeni()                           { return prijmeni; }
                public void     setPrijmeni(String prijmeni)            { this.prijmeni = prijmeni; }

                public String   getEmail()                              { return email; }
                public void     setEmail(String email)                  { this.email = email; }

                public String   getTelefon()                            { return telefon; }
                public void     setTelefon(String telefon)              { this.telefon = telefon; }

                public String   getUliceACislo()                        { return uliceACislo; }
                public void     setUliceACislo(String uliceACislo)      { this.uliceACislo = uliceACislo; }

                public String   getMesto()                              { return mesto; }
                public void     setMesto(String mesto)                  { this.mesto = mesto; }

                public String   getPsc()                                { return psc; }
                public void     setPsc(String psc)                      { this.psc = psc; }

        //endregion

}
