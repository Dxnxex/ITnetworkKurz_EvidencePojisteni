package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojistenychDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojistenychEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidencePojistenychMapper;
import cz.dxnxex.evidencepojisteni.repository.EvidencePojistenychRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidencePojistenychService {

    @Autowired
    private EvidencePojistenychRepository repository;

    @Autowired
    private EvidencePojistenychMapper mapper;


    //Vytvoření uživatele
    public void createUser(EvidencePojistenychDTO data) {
        EvidencePojistenychEntity newUser = mapper.toEntity(data);
        repository.saveAndFlush(newUser);
    }


    //List uživatelů
    public List<EvidencePojistenychDTO> getAllStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }

    //Smazání uživatele
    public void userDelete(Long id) {
        repository.deleteById(id);
    }

}
