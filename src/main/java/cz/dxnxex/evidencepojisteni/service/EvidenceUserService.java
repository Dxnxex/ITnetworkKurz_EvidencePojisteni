package cz.dxnxex.evidencepojisteni.service;


import cz.dxnxex.evidencepojisteni.dto.EvidenceAccountDTO;
import cz.dxnxex.evidencepojisteni.dto.EvidenceUserDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceAccountEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserInsuranceEntity;
import cz.dxnxex.evidencepojisteni.exeption.DuplicateEmailException;
import cz.dxnxex.evidencepojisteni.exeption.PasswordsDoNotEqualException;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUserInsuranceMapper;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUserMapper;
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
    private EvidenceUserRepository userRepository;

    @Autowired
    private EvidenceInsuranceRepository insuranceRepository;

    @Autowired
    private EvidenceUserInsuranceRepository evidenceUserInsuranceRepository;

    @Autowired
    private EvidenceAccountRepository accountRepository;

    @Autowired
    private EvidenceUserMapper userMapper;

    @Autowired
    private EvidenceUserInsuranceMapper evidenceUserInsuranceMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** VYTVOŘENÍ POJIŠTĚNÉHO DO DATABÁZE
     */
    public void userCreate(EvidenceUserDTO data) {

        EvidenceUserEntity uzivatel = userMapper.toEntity(data);

            //region Vypsání do konzole

            System.out.println();
            System.out.println( "Vytvoření uživatele: " + uzivatel);

            //endregion

        userRepository.saveAndFlush(uzivatel);
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
            accountRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }


    /** VÝPIS VŠECH POJIŠTĚNÝCH V DATABÁZI
     */
    public List<EvidenceUserDTO> userGetAllList() {

        List<EvidenceUserDTO> uzivatele = userRepository.findAll().stream().map(entita -> userMapper.toDTO(entita)).toList();

        //region Vypsání do konzole

        System.out.println();
        System.out.println(uzivatele);
        System.out.println("Výpis všech uživatelů:");

        for(EvidenceUserDTO vypis : uzivatele){
            System.out.println("ID:" + vypis.getId() + " '" + vypis.getName() + " " + vypis.getSurname()+"' - " + vypis);
        }

        //endregion

        return uzivatele;
    }


    /** VYMAZÁNÍ POJIŠTĚNÉHO Z DATABÁZE
     */
    public void userDelete(Long userID) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Vymazání uživatele s ID: " + userID);

        //endregion

            userRepository.deleteById(userID);

    }

    /** ZÍSKÁNÍ ID POJIŠTĚNÉHO
     */
    public EvidenceUserEntity userGetID(Long userID) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu uživatele ID: " + userID);

        //endregion

            return userRepository.findById(userID).orElse(null);

    }

    /** VRÁCENÍ LISTU VŠECH POJIŠTĚNÍ
     */
    public List<EvidenceInsuranceEntity> insuranceFindAllList() {

        List<EvidenceInsuranceEntity> pojisteni = insuranceRepository.findAll();

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
    public EvidenceInsuranceEntity insuranceGetID(Long insuranceID) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění ID: " + insuranceID);

        //endregion

        return insuranceRepository.findById(insuranceID).orElse(null);

    }

    /** PŘÍDÁ POJIŠTĚNÍ POJIŠTĚNÉMU
     */
    public void userAddInsurance(Long userID, Long insuranceID, int value) {

        //IDčka uživatele a vybraného pojištění
        EvidenceUserEntity              uzivatel = userGetID(userID);
        EvidenceInsuranceEntity         pojisteni = insuranceGetID(insuranceID);

        //Nová instance pro spojení
        EvidenceUserInsuranceEntity     uzivatelPojisteni = new EvidenceUserInsuranceEntity();

        //Nastavení hodnot
        uzivatelPojisteni.setUser(uzivatel);
        uzivatelPojisteni.setInsurance(pojisteni);
        uzivatelPojisteni.setValue(value);

        //Přidání pojištění uživateli
        uzivatel.getUserInsurances().add(uzivatelPojisteni);

        // Uložení uživatele
        userRepository.save(uzivatel);

    }

    /** UPRAVÍ POJIŠTĚNÍ POJIŠTĚNÉMU
     */
    public void userEditInsurance(Long userInsuranceID, int value) {

        // Najdi záznam v tabulce spojení
        Optional<EvidenceUserInsuranceEntity> optionalUzivatelPojisteni = evidenceUserInsuranceRepository.findById(userInsuranceID);

        if (optionalUzivatelPojisteni.isPresent()) {
            EvidenceUserInsuranceEntity uzivatelPojisteni = optionalUzivatelPojisteni.get();

            // Aktualizace částky
            uzivatelPojisteni.setValue(value);

            // Uložení změn
            evidenceUserInsuranceRepository.save(uzivatelPojisteni);
        } else {
            throw new EntityNotFoundException("Spojení uživatel a pojištění nebylo nalezeno.");
        }

    }

    /** VRÁTÍ LIST POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public List<EvidenceUserInsuranceEntity> userInsuranceGetIDList(Long userID) {
        return evidenceUserInsuranceRepository.findAll().stream().filter(entity -> entity.getUser().getId().equals(userID)).toList();
    }

    /** VRÁTÍ ID POJIŠTĚNÍ POJIŠTĚNÉHO
     */
    public EvidenceUserInsuranceEntity userInsuranceGetID(Long uzivatelPojisteni) {

        //region Vypsání do konzole

        System.out.println();
        System.out.println("Zobrazení detailu pojištění uživatele ID: " + uzivatelPojisteni);

        //endregion

        return evidenceUserInsuranceRepository.findById(uzivatelPojisteni).orElse(null);

    }

    /** VYMAZÁNÍ POJIŠTĚNÍ POJIŠTĚNÉHO Z DATABÁZE
     */
    public void userDeleteUzivatelPojisteni(Long userID) {

        //region [Vymazání uživatele s ID:]

        System.out.println();
        System.out.println("Vymazání uživatelského pojištění s ID: " + userID);

        //endregion

        evidenceUserInsuranceRepository.deleteById(userID);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));

    }

}










