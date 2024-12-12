package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUzivatelMapper;
import cz.dxnxex.evidencepojisteni.service.EvidenceUzivatelService;
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
@RequestMapping("uzivatele")
public class EvidenceUzivatelController  {

    private final String returnPage = "pages/uzivatele/";
    private final String redirectPage = "redirect:/uzivatele";

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidenceUzivatelService service;

    @Autowired
    private EvidenceUzivatelMapper mapper;


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
     * @param uzivatel
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
        public String renderPersonCreate(@ModelAttribute("vytvoreniUzivatele") EvidenceUzivatelDTO uzivatel) {

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
        public String createPerson(@Valid @ModelAttribute("vytvoreniUzivatele") EvidenceUzivatelDTO evidence, BindingResult result, RedirectAttributes redirectAttributes) {

            if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

                redirectAttributes.addFlashAttribute("vytvoreniUzivatele", evidence);
                return redirectPage + "/" + "create";


            } else {

                service.userCreate(evidence);
                redirectAttributes.addFlashAttribute("success", "Uživatel vytvořen.");

                return redirectPage + "";
            }


        }

    /**
     * ZOBRAZENÍ DETAILU POJIŠTĚNCE
     * @param id
     * @param model
     * @param uzivatelData
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public String renderPersonDetail(@PathVariable Long id, Model model, EvidenceUzivatelDTO uzivatelData) {

        // Získání uživatele a seznamu pojištění
        EvidenceUzivatelEntity uzivatel = service.userGetID(id);
        List<EvidencePojisteniEntity> pojisteni = service.insuranceFindAllList();

        model.addAttribute("uzivatel",              uzivatel);
        model.addAttribute("pojisteni",             pojisteni);
        model.addAttribute("castka",                null);
        model.addAttribute("uzivatelovaPojisteni",  service.userInsuranceGetIDList(id));


        return returnPage + "detail";
    }

    /**
     * VYTVOŘENÍ POJIŠTĚNÍ A NÁSLEDNÉ PŘÍŘAZENÍ K POJIŠTĚNCI
     * @param id
     * @param pojisteniId
     * @param castka
     * @return
     */
    @PostMapping("/{id}")
    public String createPersonInsurance(@PathVariable Long id, @RequestParam Long pojisteniId, @RequestParam int castka, RedirectAttributes redirectAttributes) {

        service.userAddInsurance(id, pojisteniId, castka);
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
    public String editPerson(@PathVariable long id,@Valid EvidenceUzivatelDTO evidence,BindingResult result, RedirectAttributes redirectAttributes) {

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

