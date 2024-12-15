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
        System.out.printf("\n"+entity);

        return entity;

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

        List<EvidenceUserDTO> allUsers = userRepository.findAll().stream().map(entity -> userMapper.toDTO(entity)).toList();

        //region Vypsání do konzole

        System.out.println();
        System.out.println(allUsers);
        System.out.println("Výpis všech uživatelů:");

        for(EvidenceUserDTO vypis : allUsers){
            System.out.println("ID:" + vypis.getId() + " '" + vypis.getName() + " " + vypis.getSurname()+"' - " + vypis);
        }

        //endregion

        return allUsers;
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

    /** VRÁCENÍ LISTU VŠECH POJIŠTĚNÍ
     */
    public List<EvidenceInsuranceEntity> getAllInsurancesList() {

        List<EvidenceInsuranceEntity> insurances = insuranceRepository.findAll();

        //region Vypsání do konzole

            System.out.println("\nDostupná pojištění: " );

            for(EvidenceInsuranceEntity vypis : insurances){
                System.out.println("ID:" + vypis.getId() + " - " + vypis.getName());
            }

            //endregion

        return insurances;
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
     * Updates the insurance value for a specific user by finding the corresponding
     * insurance record and updating its value.
     *
     * @param userInsuranceID the ID of the user insurance entity to be modified
     * @param value the new value to update in the user insurance record
     * @throws EntityNotFoundException if the user insurance record with the given ID is not found
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
     * Retrieves a list of EvidenceUserInsuranceEntity objects associated with the specified user ID.
     *
     * @param userID The ID of the user for whom the insurance entities are to be retrieved.
     * @return A list of EvidenceUserInsuranceEntity objects linked to the given user ID.
     */
    public List<EvidenceUserInsuranceEntity> userInsuranceGetIDList(Long userID) {
        return userInsuranceRepository.findAll().stream().filter(entity -> entity.getUser().getId().equals(userID)).toList();
    }

    /**
     * Retrieves a user's insurance details based on their insurance ID.
     *
     * @param userInsuranceID The unique identifier of the user's insurance.
     * @return The user's insurance entity if found, or null if no entity is found with the given ID.
     */
    public EvidenceUserInsuranceEntity userInsuranceGetID(Long userInsuranceID) {

                //region Vypsání do konzole

                System.out.println();
                System.out.println("Zobrazení detailu pojištění uživatele ID: " + userInsuranceID);

                //endregion

        return userInsuranceRepository.findById(userInsuranceID).orElse(null);

    }



    /**
     * Deletes user insurance associated with the given user ID.
     *
     * @param userID The unique identifier of the user whose insurance is to be deleted.
     */
    public void deleteUserInsurance(Long userID) {

            //region [Vymazání uživatele s ID:]

            System.out.println();
            System.out.println("Vymazání uživatelského pojištění s ID: " + userID);

            //endregion

        userInsuranceRepository.deleteById(userID);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));

    }

}