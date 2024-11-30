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

    //Vytvoření uživatele
    public void createUser(EvidenceUzivatelDTO data) {

        EvidenceUzivatelEntity uzivatel = mapper.toEntity(data);

            //region Vypsání vytvoření uživatele do konzole

            System.out.println();
            System.out.println("Vytvoření uživatele: " + uzivatel);

            //endregion

        repository.saveAndFlush(uzivatel);
    }

    //List uživatelů
    public List<EvidenceUzivatelDTO> userGetAll() {

        List<EvidenceUzivatelDTO> uzivatele = repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

            //region Vypsání všech uživatelů do konzole
            System.out.println();
            System.out.println("Výpis uživatelů:");
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
    public EvidenceUzivatelEntity userGet(Long uzivatel) {

            //region Vypsání zobrazení detailu uživatele do konzole

            System.out.println();
            System.out.println("Zobrazení detailu uživatele ID: " + uzivatel);

            //endregion

        return repository.findById(uzivatel).orElse(null);
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



}
