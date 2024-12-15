package cz.dxnxex.evidencepojisteni.service;

import cz.dxnxex.evidencepojisteni.dto.EvidenceInsuranceDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceInsuranceMapper;
import cz.dxnxex.evidencepojisteni.repository.EvidenceInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceInsuranceService {

    @Autowired
    private EvidenceInsuranceRepository repository;

    @Autowired
    private EvidenceInsuranceMapper mapper;


    /**
     * VYTVOŘENÍ POJIŠTĚNÍ
     * @param data
     */
    public void insuranceCreate(EvidenceInsuranceDTO data) {
        EvidenceInsuranceEntity newItem = mapper.toEntity(data);
        repository.saveAndFlush(newItem);
    }


    /**
     * VRÁTÍ LIST VŠECH POJIŠTĚNÍ
     * @return
     */
    public List<EvidenceInsuranceDTO> insuranceGetAllList() {

        List<EvidenceInsuranceDTO> insurance = repository.findAll().stream().map(entity -> mapper.toDTO(entity)).toList();

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Výpis všech pojištění:");
        for(EvidenceInsuranceDTO list : insurance){
            System.out.println("ID:" + list.getId() + " '" + list.getName() + "' - " + list);
        }

        //endregion

        return insurance;
    }


    /**
     * VRÁTÍ LIST VŠECH POJIŠTĚNÍ - STREAM
     * @return
     */
    public List<EvidenceInsuranceDTO> insuranceGetAllListStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }


    /**
     * VRÁTÍ ID POJIŠTĚNÍ
     * @param pojisteni
     * @return
     */
    public EvidenceInsuranceEntity insuranceGetID(Long pojisteni) {

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
