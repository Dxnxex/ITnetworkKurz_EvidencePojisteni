package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUzivatelMapper;
import cz.dxnxex.evidencepojisteni.repository.EvidencePojisteniRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceUzivatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceUzivatelService {

    @Autowired
    private EvidenceUzivatelRepository repository;

    @Autowired
    private EvidencePojisteniRepository repositoryPojisteni;

    @Autowired
    private EvidenceUzivatelMapper mapper;

    /**
     * Vytvoření uživatele na základě DAT, které přijme z formuláře
     * @param data
     */
    //Vytvoření uživatele
    public void userCreate(EvidenceUzivatelDTO data) {

        EvidenceUzivatelEntity uzivatel = mapper.toEntity(data);

            //region Vypsání vytvoření uživatele do konzole

            System.out.println();
            System.out.println("Vytvoření uživatele: " + uzivatel);

            //endregion

        repository.saveAndFlush(uzivatel);
    }

    /**
     * Vrátí výpiš všech uživatelů z databázate (entity), která je převede do DTO objektu a následně do listu
     * @return uživatele v DTO objektu
     */
    //Výpis všech uživatelů
    public List<EvidenceUzivatelDTO> userGetAll() {

        List<EvidenceUzivatelDTO> uzivatele = repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

            //region Vypsání všech uživatelů do konzole
            System.out.println();
            System.out.println("Výpis všech uživatelů:");
            for(EvidenceUzivatelDTO vypis : uzivatele){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getJmeno() + " " + vypis.getPrijmeni()+"' - " + vypis);
            }

            //endregion

        return uzivatele;
    }

    //Smazání uživatele
    public void userDelete(Long id) {

            //region Vypsání smazání uživatele do konzole

            System.out.println();
            System.out.println("Vymazání uživatele s ID: " + id);

            //endregion

        repository.deleteById(id);

    }

    //Detail uživatele
    public EvidenceUzivatelEntity userGetID(Long uzivatel) {

            //region Vypsání zobrazení detailu uživatele do konzole

            System.out.println();
            System.out.println("Zobrazení detailu uživatele ID: " + uzivatel);

            //endregion

        return repository.findById(uzivatel).orElse(null);

    }

    //Detail uživatele #2
    public EvidenceUzivatelDTO userGetID2(Long uzivatel) {

        //region Vypsání zobrazení detailu uživatele do konzole
        System.out.println();
        System.out.println("Zobrazení detailu uživatele ID: (#2) " + uzivatel);

        //endregion

        EvidenceUzivatelEntity pojisteniDleID = repository
                .findById(uzivatel)
                .orElseThrow();
        return mapper.toDTO(pojisteniDleID);

    }

    //Získat výpis všech pojištění
    public List<EvidencePojisteniEntity> pojisteniFindAll() {

        List<EvidencePojisteniEntity> pojisteni = repositoryPojisteni.findAll();

            //region Vypsání všech pojištění do konzole

            System.out.println();
            System.out.println("Výpis dostupných pojištění:");
            for(EvidencePojisteniEntity vypis : pojisteni){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getPredmet()+"' - " + vypis);
            }

            //endregion

        return pojisteni;
    }


    public void edit(EvidenceUzivatelDTO article) {
        EvidenceUzivatelEntity fetchedArticle = repository
                .findById(article.getId())
                .orElseThrow();

        mapper.updateEvidenceEntity(article, fetchedArticle);
        repository.save(fetchedArticle);
    }


    public void updateUser(EvidenceUzivatelDTO data) {

        //Stream - Najdi podle ID
        EvidenceUzivatelEntity fetchedArticle = repository
                .findById(data.getId())
                .orElseThrow();

        mapper.updateEvidenceEntity(data, fetchedArticle);
        repository.save(fetchedArticle);

    }


    public EvidencePojisteniEntity pojisteniFindById(Long id) {
        return repositoryPojisteni.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pojištění nenalezeno."));
    }

    public void saveUzivatel(EvidenceUzivatelEntity uzivatel) {
        repository.save(uzivatel);
    }



}










