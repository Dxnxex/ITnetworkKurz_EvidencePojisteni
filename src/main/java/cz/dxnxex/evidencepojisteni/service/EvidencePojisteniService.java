package cz.dxnxex.evidencepojisteni.service;

import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import cz.dxnxex.evidencepojisteni.repository.EvidencePojisteniRepository;
import cz.dxnxex.evidencepojisteni.mapper.EvidencePojisteniMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidencePojisteniService {

    @Autowired
    private EvidencePojisteniRepository repository;

    @Autowired
    private EvidencePojisteniMapper mapper;


    //Vytvoření uživatele
    public void createItem(EvidencePojisteniDTO data) {
        EvidencePojisteniEntity newItem = mapper.toEntity(data);
        repository.saveAndFlush(newItem);
    }

    /**
     * Vrátí výpiš všech pojištění z databázate (entity), která je převede do DTO objektu a následně do listu
     * @return pojištění v DTO objektu
     */
    //Výpis všech uživatelů
    public List<EvidencePojisteniDTO> pojisteniGetAll() {

        List<EvidencePojisteniDTO> pojisteni = repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

        //region Vypsání všech uživatelů do konzole
        System.out.println();
        System.out.println("Výpis všech pojištění:");
        for(EvidencePojisteniDTO vypis : pojisteni){
            System.out.println("ID:" + vypis.getId() + " '" + vypis.getPredmet() + "' - " + vypis);
        }

        //endregion

        return pojisteni;
    }


    //List uživatelů
    public List<EvidencePojisteniDTO> getAllStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }


    //Detail uživatele
    public EvidencePojisteniEntity pojisteniGetID(Long pojisteni) {

        //region Vypsání zobrazení detailu uživatele do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění ID: " + pojisteni);

        //endregion

        return repository.findById(pojisteni).orElse(null);

    }

    //Smazání pojisteni
    public void pojisteniDelete(Long id) {

        //region Vypsání smazání pojištění do konzole

        System.out.println();
        System.out.println("Vymazání pojištění s ID: " + id);

        //endregion

        repository.deleteById(id);

    }

}
