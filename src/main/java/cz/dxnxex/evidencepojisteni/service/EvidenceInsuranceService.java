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
     * Vytvoří nové pojištění z dat v HTML
     *
     * @param data Data pojištění, které ze kterých má být vytvořeno.
     */
    public void insuranceCreate(EvidenceInsuranceDTO data) {
        repository.saveAndFlush(mapper.toEntity(data));
    }


    /** 
     * Získá seznam všech pojištění.
     *
     * @return Seznam objektů EvidenceInsuranceDTO představující všechna pojištění.
     */
    public List<EvidenceInsuranceDTO> insuranceGetAllList() {

        //Logic
        List<EvidenceInsuranceDTO> insurance = repository.findAll().stream().map(entity -> mapper.toDTO(entity)).toList();

        //LOG
        System.out.println("\nVýpis všech pojištění:");
        for (EvidenceInsuranceDTO list : insurance) {
            System.out.println("ID:" + list.getId() + " '" + list.getName() + "' - " + list);
        }

        return insurance;
    }


    /** 
     * Získá pojištění na základě ID.
     *
     * @param insurance ID pojištění, které se má načíst.
     * @return EvidenceInsuranceEntity odpovídající zadanému ID.
     * @throws IllegalArgumentException pokud pojištění s daným ID nebylo nalezeno.
     */
    public EvidenceInsuranceEntity insuranceGetID(Long insurance) {

        System.out.println("\nZobrazení detailu pojištění ID: " + insurance);

        return repository.findById(insurance)
                .orElseThrow(() -> new IllegalArgumentException("Pojištění s ID " + insurance + " nebylo nalezeno."));
    }

    /** 
     * Vymaže pojištění na základě ID.
     *
     * @param insuranceID ID pojištění, které se má smazat.
     */
    public void insuranceDelete(Long insuranceID) {

        System.out.println("\nVymazání pojištění s ID: " + insuranceID);
        repository.deleteById(insuranceID);

    }

}
