package cz.dxnxex.evidencepojisteni.dto;


public class EvidencePojisteniDTO {


    private Long id;               // Unikátní identifikátor
    private String predmet;        // Předmět pojištění
    private String popis;          // Text pojištění
    private String castka;           // Text pojištění


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {this.popis = popis;}


    public String getCastka() {
        return castka;
    }

    public void setCastka(String castka) {
        this.castka = castka;
    }
}


/*


 */