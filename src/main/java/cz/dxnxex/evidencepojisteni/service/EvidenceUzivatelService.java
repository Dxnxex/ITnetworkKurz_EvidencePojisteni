package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelPojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelPojisteniEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUzivatelMapper;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUzivatelPojisteniMapper;
import cz.dxnxex.evidencepojisteni.repository.EvidencePojisteniRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceUzivatelPojisteniRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceUzivatelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvidenceUzivatelService  {



    @Autowired
    private EvidenceUzivatelRepository repositoryUzivatel;

    @Autowired
    private EvidencePojisteniRepository repositoryPojisteni;

    @Autowired
    private EvidenceUzivatelPojisteniRepository repositoryUzivatelPojisteni;



    @Autowired
    private EvidenceUzivatelMapper mapper;

    @Autowired
    private EvidenceUzivatelPojisteniMapper mapperUzivatelPojisteni;




    /** VYTVOŘENÍ UŽIVATELE DO DATABÁZE
     * Vytvoření uživatele na základě DAT, které přijme z formuláře
     * @param data
     */
    public void userCreate(EvidenceUzivatelDTO data) {

        EvidenceUzivatelEntity uzivatel = mapper.toEntity(data);

            //region [Vytvoření uživatele:]
            System.out.println();
            System.out.println( "Vytvoření uživatele: " + uzivatel);

            //endregion

        repositoryUzivatel.saveAndFlush(uzivatel);
    }


    /** VÝPIS VŠECH UŽIVATELŮ V DATABÁZI
     * Vrátí výpis všech uživatelů z databázate (entity), která je převede do DTO objektu a následně do listu
     * @return uživatele v DTO objektu
     */
    public List<EvidenceUzivatelDTO> userGetAll() {

        List<EvidenceUzivatelDTO> uzivatele = repositoryUzivatel.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

            //region [Výpis všech uživatelů:]

            System.out.println();
            System.out.println("Výpis všech uživatelů:");

            for(EvidenceUzivatelDTO vypis : uzivatele){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getJmeno() + " " + vypis.getPrijmeni()+"' - " + vypis);
            }

            //endregion

        return uzivatele;
    }

    /** VYMAZÁNÍ UŽIVATELE Z DATABÁZE
     * Smaže daného uživatele z databáze
     * @param id
     */
    public void userDelete(Long id) {

        //region [Vymazání uživatele s ID:]

        System.out.println();
        System.out.println("Vymazání uživatele s ID: " + id);

        //endregion

            //CODE
            repositoryUzivatel.deleteById(id);

    }

    //Detail uživatele
    public EvidenceUzivatelEntity userGetID(Long uzivatel) {

        //region [Zobrazení detailu uživatele ID:]

        System.out.println();
        System.out.println("Zobrazení detailu uživatele ID: " + uzivatel);

        //endregion

            //CODE
            return repositoryUzivatel.findById(uzivatel).orElse(null);

    }

    /**
     * Vypsání všech pojištění do konzole
     * @return
     */
    public List<EvidencePojisteniEntity> pojisteniFindAll() {

        List<EvidencePojisteniEntity> pojisteni = repositoryPojisteni.findAll();

            //region Vypsání všech pojištění do konzole

            System.out.println("DOSTUPNÁ POJIŠTĚNÍ");
            for(EvidencePojisteniEntity vypis : pojisteni){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getPredmet()+"' - " + vypis);
            }

            //endregion

        return pojisteni;
    }

    //Najít ID pojištění
    public EvidencePojisteniEntity pojisteniGetID(Long pojisteni) {

        //region [Zobrazení detailu pojištění ID:]

        System.out.println();
        System.out.println("Zobrazení detailu pojištění ID: " + pojisteni);

        //endregion

        //CODE
        return repositoryPojisteni.findById(pojisteni).orElse(null);

    }


    public void pridatPojisteniUzivateli(Long uzivatelId, Long pojisteniId, int castka) {

        //IDčka uživatele a vybraného pojištění
        EvidenceUzivatelEntity uzivatel = userGetID(uzivatelId);
        EvidencePojisteniEntity pojisteni = pojisteniGetID(pojisteniId);

        //Nová instance pro spojení
        EvidenceUzivatelPojisteniEntity uzivatelPojisteni = new EvidenceUzivatelPojisteniEntity();

        //Nastavení hodnot
        uzivatelPojisteni.setUzivatel(uzivatel);
        uzivatelPojisteni.setPojisteni(pojisteni);
        uzivatelPojisteni.setCastka(castka);


        //Přidání pojištění uživateli
        uzivatel.getUzivatelovaPojisteni().add(uzivatelPojisteni);

        // Uložení uživatele
        repositoryUzivatel.save(uzivatel);

    }


    public void upravitPojisteniUzivateli(Long uzivatelPojisteniId, int castka) {

        // Najdi záznam v tabulce spojení
        Optional<EvidenceUzivatelPojisteniEntity> optionalUzivatelPojisteni = repositoryUzivatelPojisteni.findById(uzivatelPojisteniId);

        if (optionalUzivatelPojisteni.isPresent()) {
            EvidenceUzivatelPojisteniEntity uzivatelPojisteni = optionalUzivatelPojisteni.get();

            // Aktualizace částky
            uzivatelPojisteni.setCastka(castka);

            // Uložení změn
            repositoryUzivatelPojisteni.save(uzivatelPojisteni);
        } else {
            throw new EntityNotFoundException("Spojení uživatel a pojištění nebylo nalezeno.");
        }

    }

    //Nalezení pojištění dle ID uživatele
    public List<EvidenceUzivatelPojisteniEntity> getPojisteniDleID(Long id) {
        return repositoryUzivatelPojisteni.findAll().stream().filter(entity -> entity.getUzivatel().getId() == id).toList();
    }

    //Detail pojištění uživatele
    public EvidenceUzivatelPojisteniEntity userPojisteniGetID(Long uzivatelPojisteni) {

        //region [Zobrazení detailu uživatele ID:]

        System.out.println();
        System.out.println("Zobrazení detailu pojištění uživatele ID: " + uzivatelPojisteni);

        //endregion

        //CODE
        return repositoryUzivatelPojisteni.findById(uzivatelPojisteni).orElse(null);

    }

    /** VYMAZÁNÍ UŽIVATELSKÉHO POJIŠTĚNÍ Z DATABÁZE
     * Smaže daného uživatele z databáze
     * @param id
     */
    public void userDeleteUzivatelPojisteni(Long id) {

        //region [Vymazání uživatele s ID:]

        System.out.println();
        System.out.println("Vymazání uživatelského pojištění s ID: " + id);

        //endregion

        //CODE
        repositoryUzivatelPojisteni.deleteById(id);

    }

}










