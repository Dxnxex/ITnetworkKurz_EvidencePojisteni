package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidenceConfiguration;
import cz.dxnxex.evidencepojisteni.EvidenceRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidenceUserDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUserMapper;
import cz.dxnxex.evidencepojisteni.service.EvidenceUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("user")
public class EvidenceUserController {

    private final String returnPage = "pages/" + EvidenceConfiguration.getPathUser() + "/" ;
    private final String redirectPage = "redirect:/" + EvidenceConfiguration.getPathUser();

    private final EvidenceRedirect redirect = new EvidenceRedirect();

    @Autowired
    private EvidenceUserService serviceUser;

    @Autowired
    private EvidenceUserMapper mapperUser;


    /**
     * ZOBRAZENÍ HLAVNÍ STRÁNKY POJIŠTĚNCŮ
     * @param model
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
        public String renderPerson(Model model) {

            model.addAttribute("vypsatVsechnyUzivatele", serviceUser.userGetAllList());
            return returnPage + "index";

        }

    /**
     * ZOBRAZENÍ VYTVOŘENÍ POJIŠTĚNCE
     * @param user uživatelská data
     * @return vrácení na stránku
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
        public String renderPersonCreate(@ModelAttribute("vytvoreniUzivatele") EvidenceUserDTO user) {

        return returnPage + "create";

        }


    /**
     * ZOBRAZENÍ FORMULÁŘE PRO VYTVOŘENÍ POJIŠTĚNCE
     * @param data
     * @param result
     * @param redirectAttributes
     * @return
     */

    @PreAuthorize("isAuthenticated()")
        @PostMapping("/create")
        public String createPerson(@Valid @ModelAttribute("vytvoreniUzivatele") EvidenceUserDTO data, BindingResult result, RedirectAttributes redirectAttributes) {

            if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

                redirectAttributes.addFlashAttribute("vytvoreniUzivatele", data);
                return redirectPage + "/" + "create";


            } else {

                serviceUser.userCreate(data);
                redirectAttributes.addFlashAttribute("success", "Uživatel vytvořen.");

                return redirectPage;
            }


        }

        // TODO: Dodělat (TEST)

    /**
     * ZOBRAZENÍ DETAILU POJIŠTĚNCE
     * @param id
     * @param model
     * @param userData
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public String renderPersonDetail(@PathVariable Long id, Model model, EvidenceUserDTO userData) {

        // Získání uživatele a seznamu pojištění
        EvidenceUserEntity user = serviceUser.userGetID(id);
        List<EvidenceInsuranceEntity> insurance = serviceUser.insuranceFindAllList();

        model.addAttribute("user",          user);
        model.addAttribute("insurance",     insurance);
        model.addAttribute("value",         null);
        model.addAttribute("userInsurance",  serviceUser.userInsuranceGetIDList(id));


        return returnPage + "detail";
    }

    /**
     * VYTVOŘENÍ POJIŠTĚNÍ A NÁSLEDNÉ PŘÍŘAZENÍ K POJIŠTĚNCI
     * @param id
     * @param insuranceID
     * @param value
     * @return
     */
    @PostMapping("/{id}")
    public String createPersonInsurance(@PathVariable Long id, @RequestParam Long insuranceID, @RequestParam int value, RedirectAttributes redirectAttributes) {

        serviceUser.userAddInsurance(id, insuranceID, value);
        redirectAttributes.addFlashAttribute("success", "Uživatelovo pojištění vytvořeno");
        return redirectPage + "/" + id;
    }




    /**
     * ZOBRAZENÍ ÚPRAVY POJIŠTĚNÉHO
     * @param id
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{id}")
    public String renderEditPerson(@PathVariable Long id, Model model) {

        model.addAttribute("user", serviceUser.userGetID(id));
        return returnPage + "edit";

    }

    /**
     * ÚPRAVA POJIŠTĚNÉHO
     * @param id
     * @param evidence
     * @param result
     * @param redirectAttributes
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{id}")
    public String editPerson(@PathVariable long id, @Valid EvidenceUserDTO evidence, BindingResult result, RedirectAttributes redirectAttributes) {

        if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

            redirectAttributes.addFlashAttribute("uzivatel", evidence);
            return redirectPage + "edit/{id}";

        } else {

            serviceUser.userCreate(evidence);
            redirectAttributes.addFlashAttribute("success", "Uživatel upraven");
            return redirectPage;
        }


    }

    /**
     * SMAZÁNÍ POJIŠTĚNÉHO
     * @param id
     * @param redirectAttributes
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id, RedirectAttributes redirectAttributes){

        serviceUser.userDelete(id);
        redirectAttributes.addFlashAttribute("delete", "Uživatel smazán.");
        return redirectPage + "";


    }








}

