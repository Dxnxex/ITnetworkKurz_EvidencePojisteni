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


    //Vytvoření uživatele
    public void createItem(EvidencePojisteniDTO data) {
        EvidencePojisteniEntity newItem = mapper.toEntity(data);
        repository.saveAndFlush(newItem);
    }


    //List uživatelů
    public List<EvidencePojisteniDTO> getAllStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }

    //Smazání uživatele
    public void itemDelete(Long id) {
        repository.deleteById(id);
    }

}
