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
    private EvidenceUserInsuranceRepository userInsuranceRepository;

    @Autowired
    private EvidenceAccountRepository accountRepository;

    @Autowired
    private EvidenceUserMapper userMapper;

    @Autowired
    private EvidenceUserInsuranceMapper userInsuranceMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /** 
     * Vytvoří nového uživatele a uloží ho do databáze.
     *
     * @param data Data nového uživatele ve formátu ENTITY
     */
    public EvidenceUserEntity userCreate(EvidenceUserDTO data) {

        //Logic
        EvidenceUserEntity entity =userRepository.saveAndFlush(userMapper.toEntity(data));

        //LOG
        System.out.printf("\nVytvoření nového uživavatele: "+entity);

        return entity;

    }


    /** 
     * Metoda vytvoří nového uživatele na základě zadaných dat, zašifruje heslo
     * a nastaví, zda má být uživatelský účet administrátorský.
     *
     * @param user    Data nového uživatele ve formě DTO.
     * @param isAdmin Určuje, zda má mít uživatel administrátorská práva.
     * @throws PasswordsDoNotEqualException Pokud zadané heslo a jeho potvrzení nejsou stejné.
     * @throws DuplicateEmailException      Pokud již existuje uživatel se stejným e-mailem.
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


    /** 
     * VÝPIS VŠECH POJIŠTĚNÝCH V DATABÁZI
     * Metoda získá seznam všech pojištěných osob v databázi a vrátí jej ve formě DTO objektů.
     *
     * @return Seznam všech uživatelů jako EvidenceUserDTO.
     */
    public List<EvidenceUserDTO> userGetAllList() {

        //Logic
        List<EvidenceUserDTO> allUsers = userRepository.findAll().stream().map(entity -> userMapper.toDTO(entity)).toList();

        //LOG
        System.out.println("\nVýpis všech uživatelů:");
            for(EvidenceUserDTO vypis : allUsers){
                System.out.println("ID:" + vypis.getId() + " '" + vypis.getName() + " " + vypis.getSurname()+"' - " + vypis);
            }

        return allUsers;
    }


    /** 
     * Vymaže uživatele na základě ID.
     *
     * @param userID ID uživatele, které se má smazat.
     */
    public void userDelete(Long userID) {

        System.out.println("\nVymazání uživatele s ID: " + userID);
        userRepository.deleteById(userID);

    }


    /** 
     * Získá uživatele podle jeho ID.
     *
     * @param userID ID uživatele, kterého chceme získat
     * @return Entita uživatele, pokud existuje
     */
    public EvidenceUserEntity getUserByID(Long userID) {

        //Logic
        EvidenceUserEntity entity = userRepository.findById(userID).orElseThrow(null);

        //LOG
        System.out.println("\nZvolený uživatel s: ID:"  + entity.getId() + " -  " + entity.getName() + "  " + entity.getSurname());

            return entity;

    }

    /** 
     * VRÁCENÍ SEZNAMU VŠECH POJIŠTĚNÍ
     * Metoda získá seznam všech pojištění v databázi a zobrazí je v konzoli.
     *
     * @return Seznam všech pojištění jako EvidenceInsuranceEntity.
     */
    public List<EvidenceInsuranceEntity> getAllInsurancesList() {

        //Logic
        List<EvidenceInsuranceEntity> insurances = insuranceRepository.findAll();

        //LOG
            System.out.println("\nDostupná pojištění: " );
            for(EvidenceInsuranceEntity vypis : insurances){
                System.out.println("ID:" + vypis.getId() + " - " + vypis.getName());
            }

        return insurances;
    }

    /** 
     * VRÁTÍ DETAILY POJIŠTĚNÍ PODLE ID
     * Metoda vrátí detail pojištění na základě zadaného ID.
     *
     * @param insuranceID ID pojištění, které má být načteno.
     * @return Entita pojištění, pokud existuje; jinak null.
     */
    public EvidenceInsuranceEntity insuranceGetID(Long insuranceID) {

        System.out.println("\nZobrazení detailu pojištění ID: " + insuranceID);
        return insuranceRepository.findById(insuranceID).orElse(null);

    }

    /** 
     * PŘIDÁNÍ POJIŠTĚNÍ UŽIVATELI
     * Metoda připojí konkrétní pojištění k uživateli na základě jejich ID.
     *
     * @param userID      ID uživatele, ke kterému bude připojeno pojištění.
     * @param insuranceID ID pojištění, které bude připojeno k uživateli.
     * @param value       Hodnota (např. limit nebo částka) spojená s pojištěním.
     */
    public void userAddInsurance(Long userID, Long insuranceID, int value) {

        //IDčka uživatele a vybraného pojištění
        EvidenceUserEntity                  user = getUserByID(userID);
        EvidenceInsuranceEntity         insurance = insuranceGetID(insuranceID);

        //Nová instance pro spojení
        EvidenceUserInsuranceEntity     userInsurance = new EvidenceUserInsuranceEntity();

        //Nastavení hodnot
        userInsurance.setUser(user);
        userInsurance.setInsurance(insurance);
        userInsurance.setValue(value);

        //Přidání pojištění uživateli
        user.getUserInsurances().add(userInsurance);

        // Uložení uživatele
        userRepository.save(user);

    }


    /** 
     * Upraví hodnotu pojištění uživatele na základě ID pojištění.
     *
     * @param userInsuranceID ID pojištění uživatele, které má být upraveno.
     * @param value           Nová hodnota, která má být nastavena.
     * @throws EntityNotFoundException Pokud spojení uživatel-pojištění nebylo nalezeno.
     */
    public void userEditInsurance(Long userInsuranceID, int value) {

        // Najdi záznam v tabulce spojení
        Optional<EvidenceUserInsuranceEntity> optionalUserInsurance = userInsuranceRepository.findById(userInsuranceID);

        if (optionalUserInsurance.isPresent()) {

            EvidenceUserInsuranceEntity userInsurance = optionalUserInsurance.get();

            userInsurance.setValue(value);
            userInsuranceRepository.save(userInsurance);

        } else {
            throw new EntityNotFoundException("Spojení uživatel a pojištění nebylo nalezeno.");
        }

    }


    /** 
     * Získá seznam všech pojištění uživatele na základě jeho ID.
     *
     * @param userID ID uživatele, jehož pojištění mají být získána.
     * @return Seznam entit EvidenceUserInsuranceEntity spojených s uživatelem.
     */
    public List<EvidenceUserInsuranceEntity> userInsuranceGetIDList(Long userID) {
        return userInsuranceRepository.findAll().stream().filter(entity -> entity.getUser().getId().equals(userID)).toList();
    }

    /** 
     * Vrátí detail pojištění uživatele na základě jeho ID.
     *
     * @param userInsuranceID ID pojištění uživatele, které má být vráceno.
     * @return Entita pojištění uživatele EvidenceUserInsuranceEntity, pokud existuje; jinak null.
     */
    public EvidenceUserInsuranceEntity userInsuranceGetID(Long userInsuranceID) {

                System.out.println("\nZobrazení detailu pojištění uživatele ID: " + userInsuranceID);
                return userInsuranceRepository.findById(userInsuranceID).orElse(null);

    }


    /** 
     * Vymaže pojištění uživatele spojené s daným ID uživatele.
     *
     * @param userID Jedinečný identifikátor uživatele, jehož pojištění má být smazáno.
     */
    public void deleteUserInsurance(Long userID) {

            System.out.println("\nVymazání uživatelského pojištění s ID: " + userID);
            userInsuranceRepository.deleteById(userID);

    }

    /** 
     * Načte uživatele podle uživatelského jména (emailu) z databáze.
     *
     * @param username Uživatelské jméno (email), podle kterého se bude uživatel vyhledávat.
     * @return Instance UserDetails s informacemi o nalezeném uživateli.
     * @throws UsernameNotFoundException Pokud uživatel s daným uživatelským jménem (email) nebyl nalezen.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatelské jméno " + username + " nebylo nalezeno."));
    }

}