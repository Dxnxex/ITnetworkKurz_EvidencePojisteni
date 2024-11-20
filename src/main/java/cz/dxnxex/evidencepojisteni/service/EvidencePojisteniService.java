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

    public EvidencePojisteniDTO create(EvidencePojisteniDTO data) {

        EvidencePojisteniEntity entity = mapper.toEntity(data);
        repository.saveAndFlush(entity);
        return mapper.toDTO(entity);
    }

    public EvidencePojisteniDTO createUser(EvidencePojisteniDTO data) {

        EvidencePojisteniEntity entity = mapper.toEntity(data);
        repository.saveAndFlush(entity);
        return mapper.toDTO(entity);

    }

    public void createUser2(EvidencePojisteniDTO data) {
        EvidencePojisteniEntity newUser = mapper.toEntity(data);
        repository.saveAndFlush(newUser);
    }


    //Metody
    public List<EvidencePojisteniDTO> getAllStream() {
        return repository.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();
    }

}
