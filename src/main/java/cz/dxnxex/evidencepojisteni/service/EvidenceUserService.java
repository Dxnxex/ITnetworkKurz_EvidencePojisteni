package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidenceAccountDTO;
import cz.dxnxex.evidencepojisteni.dto.EvidenceUserDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceAccountEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserInsuranceEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUserInsuranceMapper;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUserMapper;
import cz.dxnxex.evidencepojisteni.models.DuplicateEmailException;
import cz.dxnxex.evidencepojisteni.models.PasswordsDoNotEqualException;
import cz.dxnxex.evidencepojisteni.repository.EvidenceAccountRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceInsuranceRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceUserInsuranceRepository;
import cz.dxnxex.evidencepojisteni.repository.EvidenceUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenceUserService implements UserDetailsService {

    @Autowired
    private EvidenceUserRepository repositoryUzivatel;

    @Autowired
    private EvidenceInsuranceRepository repositoryPojisteni;

    @Autowired
    private EvidenceUserInsuranceRepository repositoryUzivatelPojisteni;

    @Autowired
    private EvidenceAccountRepository repositoryAccount;

    @Autowired
    private EvidenceUserMapper mapper;

    @Autowired
    private EvidenceUserInsuranceMapper mapperUzivatelPojisteni;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** VYTVOŘENÍ POJIŠTĚNÉHO DO DATABÁZE
     */
    public void userCreate(EvidenceUserDTO data) {

        EvidenceUserEntity uzivatel = mapper.toEntity(data);

            //region Vypsání do konzole

            System.out.println();
            System.out.println( "Vytvoření uživatele: " + uzivatel);

            //endregion

        repositoryUzivatel.saveAndFlush(uzivatel);
    }

    /** VYTVOŘENÍ POJIŠTĚNÉHO DO DATABÁZE (REGISTERED)
     */
    public void userCreate2(EvidenceAccountDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new PasswordsDoNotEqualException();

        EvidenceAccountEntity userEntity = new EvidenceAccountEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            repositoryAccount.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }


    /** VÝPIS VŠECH POJIŠTĚNÝCH V DATABÁZI
     */
    public List<EvidenceUserDTO> userGetAllList() {

        List<EvidenceUserDTO> uzivatele = repositoryUzivatel.findAll().stream().map(entita -> mapper.toDTO(entita)).toList();

            //region Vypsání do konzole

            System.out.println();
            System.out.println("Výpis všech uživatelů:");

            for(EvidenceUserDTO vypis : uzivatele){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getName() + " " + vypis.getSurname()+"' - " + vypis);
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
    public EvidenceUserEntity userGetID(Long uzivatel) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu uživatele ID: " + uzivatel);

        //endregion

            return repositoryUzivatel.findById(uzivatel).orElse(null);

    }

    /** VRÁCENÍ LISTU VŠECH POJIŠTĚNÍ
     */
    public List<EvidenceInsuranceEntity> insuranceFindAllList() {

        List<EvidenceInsuranceEntity> pojisteni = repositoryPojisteni.findAll();

        //region Vypsání do konzole

            System.out.println("DOSTUPNÁ POJIŠTĚNÍ");
            for(EvidenceInsuranceEntity vypis : pojisteni){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getName()+"' - " + vypis);
            }

            //endregion

        return pojisteni;
    }

    /** VRÁTÍ ID POJIŠTĚNÍ
     */
    public EvidenceInsuranceEntity insuranceGetID(Long pojisteni) {

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
        EvidenceUserEntity uzivatel = userGetID(uzivatelId);
        EvidenceInsuranceEntity pojisteni = insuranceGetID(pojisteniId);

        //Nová instance pro spojení
        EvidenceUserInsuranceEntity uzivatelPojisteni = new EvidenceUserInsuranceEntity();

        //Nastavení hodnot
        uzivatelPojisteni.setUser(uzivatel);
        uzivatelPojisteni.setInsurance(pojisteni);
        uzivatelPojisteni.setValue(castka);


        //Přidání pojištění uživateli
        uzivatel.getUserInsurances().add(uzivatelPojisteni);

        // Uložení uživatele
        repositoryUzivatel.save(uzivatel);

    }

    /** UPRAVÍ POJIŠTĚNÍ POJIŠTĚNÉMU
     */
    public void userEditInsurance(Long uzivatelPojisteniId, int castka) {

        // Najdi záznam v tabulce spojení
        Optional<EvidenceUserInsuranceEntity> optionalUzivatelPojisteni = repositoryUzivatelPojisteni.findById(uzivatelPojisteniId);

        if (optionalUzivatelPojisteni.isPresent()) {
            EvidenceUserInsuranceEntity uzivatelPojisteni = optionalUzivatelPojisteni.get();

            // Aktualizace částky
            uzivatelPojisteni.setValue(castka);

            // Uložení změn
            repositoryUzivatelPojisteni.save(uzivatelPojisteni);
        } else {
            throw new EntityNotFoundException("Spojení uživatel a pojištění nebylo nalezeno.");
        }

    }

    /** VRÁTÍ LIST POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public List<EvidenceUserInsuranceEntity> userInsuranceGetIDList(Long id) {
        return repositoryUzivatelPojisteni.findAll().stream().filter(entity -> entity.getUser().getId() == id).toList();
    }

    /** VRÁTÍ ID POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public EvidenceUserInsuranceEntity userInsuranceGetID(Long uzivatelPojisteni) {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repositoryAccount.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));

    }

}










