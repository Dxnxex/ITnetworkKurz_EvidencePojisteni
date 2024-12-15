package cz.dxnxex.evidencepojisteni.controller;


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

    private final String returnPage = "pages/user/";
    private final String redirectPage = "redirect:/user";

    private final EvidenceRedirect redirect = new EvidenceRedirect();

    @Autowired
    private EvidenceUserService service;

    @Autowired
    private EvidenceUserMapper mapper;


    /**
     * ZOBRAZENÍ HLAVNÍ STRÁNKY POJIŠTĚNCŮ
     * @param model
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
        public String renderPerson(Model model) {

            model.addAttribute("vypsatVsechnyUzivatele", service.userGetAllList());
            return returnPage + "index";

        }

    /**
     * ZOBRAZENÍ VYTVOŘENÍ POJIŠTĚNCE
     * @param user
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
        public String renderPersonCreate(@ModelAttribute("vytvoreniUzivatele") EvidenceUserDTO user) {

        return returnPage + "create";

        }


    /**
     * ZOBRAZENÍ FORMULÁŘE PRO VYTVOŘENÍ POJIŠTĚNCE
     * @param evidence
     * @param result
     * @param redirectAttributes
     * @return
     */

    @PreAuthorize("isAuthenticated()")
        @PostMapping("/create")
        public String createPerson(@Valid @ModelAttribute("vytvoreniUzivatele") EvidenceUserDTO evidence, BindingResult result, RedirectAttributes redirectAttributes) {

            if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

                redirectAttributes.addFlashAttribute("vytvoreniUzivatele", evidence);
                return redirectPage + "/" + "create";


            } else {

                service.userCreate(evidence);
                redirectAttributes.addFlashAttribute("success", "Uživatel vytvořen.");

                return redirectPage;
            }


        }

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
        EvidenceUserEntity user = service.userGetID(id);
        List<EvidenceInsuranceEntity> insurance = service.insuranceFindAllList();

        model.addAttribute("uzivatel",              user);
        model.addAttribute("pojisteni",             insurance);
        model.addAttribute("castka",                null);
        model.addAttribute("uzivatelovaPojisteni",  service.userInsuranceGetIDList(id));


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

        service.userAddInsurance(id, insuranceID, value);
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

        model.addAttribute("uzivatel", service.userGetID(id));
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

            service.userCreate(evidence);
            redirectAttributes.addFlashAttribute("success", "Uživatel upraven");
            return redirectPage + "";
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

        service.userDelete(id);
        redirectAttributes.addFlashAttribute("delete", "Uživatel smazán.");
        return redirectPage + "";


    }








}

