package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
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



    /** VYTVOŘENÍ POJIŠTĚNÉHO DO DATABÁZE
     */
    public void userCreate(EvidenceUzivatelDTO data) {

        EvidenceUzivatelEntity uzivatel = mapper.toEntity(data);

            //region Vypsání do konzole

            System.out.println();
            System.out.println( "Vytvoření uživatele: " + uzivatel);

            //endregion

        repositoryUzivatel.saveAndFlush(uzivatel);
    }


    /** VÝPIS VŠECH POJIŠTĚNÝCH V DATABÁZI
     */
    public List<EvidenceUzivatelDTO> userGetAllList() {

        List<EvidenceUzivatelDTO> uzivatele = repositoryUzivatel.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

            //region Vypsání do konzole

            System.out.println();
            System.out.println("Výpis všech uživatelů:");

            for(EvidenceUzivatelDTO vypis : uzivatele){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getJmeno() + " " + vypis.getPrijmeni()+"' - " + vypis);
            }

            //endregion

        return uzivatele;
    }

    /** VYMAZÁNÍ POJIŠTĚNÉHO Z DATABÁZE
     */
    public void userDelete(Long id) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Vymazání uživatele s ID: " + id);

        //endregion

            repositoryUzivatel.deleteById(id);

    }

    /** ZÍSKÁNÍ ID POJIŠTĚNÉHO
     */
    public EvidenceUzivatelEntity userGetID(Long uzivatel) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu uživatele ID: " + uzivatel);

        //endregion

            return repositoryUzivatel.findById(uzivatel).orElse(null);

    }

    /** VRÁCENÍ LISTU VŠECH POJIŠTĚNÍ
     */
    public List<EvidencePojisteniEntity> insuranceFindAllList() {

        List<EvidencePojisteniEntity> pojisteni = repositoryPojisteni.findAll();

        //region Vypsání do konzole

            System.out.println("DOSTUPNÁ POJIŠTĚNÍ");
            for(EvidencePojisteniEntity vypis : pojisteni){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getPredmet()+"' - " + vypis);
            }

            //endregion

        return pojisteni;
    }

    /** VRÁTÍ ID POJIŠTĚNÍ
     */
    public EvidencePojisteniEntity insuranceGetID(Long pojisteni) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění ID: " + pojisteni);

        //endregion

        return repositoryPojisteni.findById(pojisteni).orElse(null);

    }

    /** PŘÍDÁ POJIŠTĚNÍ POJIŠTĚNÉMU
     */
    public void userAddInsurance(Long uzivatelId, Long pojisteniId, int castka) {

        //IDčka uživatele a vybraného pojištění
        EvidenceUzivatelEntity uzivatel = userGetID(uzivatelId);
        EvidencePojisteniEntity pojisteni = insuranceGetID(pojisteniId);

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

    /** UPRAVÍ POJIŠTĚNÍ POJIŠTĚNÉMU
     */
    public void userEditInsurance(Long uzivatelPojisteniId, int castka) {

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

    /** VRÁTÍ LIST POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public List<EvidenceUzivatelPojisteniEntity> userInsuranceGetIDList(Long id) {
        return repositoryUzivatelPojisteni.findAll().stream().filter(entity -> entity.getUzivatel().getId() == id).toList();
    }

    /** VRÁTÍ ID POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public EvidenceUzivatelPojisteniEntity userInsuranceGetID(Long uzivatelPojisteni) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění uživatele ID: " + uzivatelPojisteni);

        //endregion

        return repositoryUzivatelPojisteni.findById(uzivatelPojisteni).orElse(null);

    }

    /** VYMAZÁNÍ POJIŠTĚNÍ POJIŠTĚNÉHO Z DATABÁZE
     */
    public void userDeleteUzivatelPojisteni(Long id) {

        //region [Vymazání uživatele s ID:]

        System.out.println();
        System.out.println("Vymazání uživatelského pojištění s ID: " + id);

        //endregion

        repositoryUzivatelPojisteni.deleteById(id);

    }

}










