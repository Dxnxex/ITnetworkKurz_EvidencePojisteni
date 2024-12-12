package cz.dxnxex.evidencepojisteni.service;

import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
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


    /**
     * VYTVOŘENÍ POJIŠTĚNÍ
     * @param data
     */
    public void insuranceCreate(EvidencePojisteniDTO data) {
        EvidencePojisteniEntity newItem = mapper.toEntity(data);
        repository.saveAndFlush(newItem);
    }


    /**
     * VRÁTÍ LIST VŠECH POJIŠTĚNÍ
     * @return
     */
    public List<EvidencePojisteniDTO> insuranceGetAllList() {

        List<EvidencePojisteniDTO> pojisteni = repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Výpis všech pojištění:");
        for(EvidencePojisteniDTO vypis : pojisteni){
            System.out.println("ID:" + vypis.getId() + " '" + vypis.getPredmet() + "' - " + vypis);
        }

        //endregion

        return pojisteni;
    }


    /**
     * VRÁTÍ LIST VŠECH POJIŠTĚNÍ - STREAM
     * @return
     */
    public List<EvidencePojisteniDTO> insuranceGetAllListStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }


    /**
     * VRÁTÍ ID POJIŠTĚNÍ
     * @param pojisteni
     * @return
     */
    public EvidencePojisteniEntity insuranceGetID(Long pojisteni) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění ID: " + pojisteni);

        //endregion

        return repository.findById(pojisteni).orElse(null);

    }

    /**
     * VYMAZÁNÍ POJIŠTĚNÍ
     * @param id
     */
    public void insuranceDelete(Long id) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Vymazání pojištění s ID: " + id);

        //endregion

        repository.deleteById(id);

    }

}
