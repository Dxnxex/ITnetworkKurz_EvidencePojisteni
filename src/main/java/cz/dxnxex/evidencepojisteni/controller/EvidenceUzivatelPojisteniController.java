package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelPojisteniEntity;
import cz.dxnxex.evidencepojisteni.service.EvidenceUzivatelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("uzivatele/pojisteni")
public class EvidenceUzivatelPojisteniController {

    private final String returnPage = "pages/uzivatele/";
    private final String redirectPage = "redirect:/uzivatele/";

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidenceUzivatelService service;


    /**
     * ZOBRAZENÍ ÚPRAVY POJIŠTĚNÍ POJIŠTĚNÉHO
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit/{id}")
    public String renderEditPersonInsurance(@PathVariable Long id, Model model) {

        // Získání uživatele a seznamu pojištění
        EvidenceUzivatelPojisteniEntity uzivatel = service.userInsuranceGetID(id);
        model.addAttribute("pojisteni",uzivatel);

        return returnPage + "pojisteniEdit";

    }

    /**
     * ÚPRAVA POJIŠTĚNÍ POJIŠTENÉMU
     * @param id
     * @param castka
     * @return
     */
    @PostMapping("edit/{id}")
    public String editPersonInsurance(@PathVariable Long id, @RequestParam int castka, RedirectAttributes redirectAttributes)  {

        Long uzivatelID = service.userInsuranceGetID(id).getUzivatel().getId();
        Long pojisteniID = service.userInsuranceGetID(id).getId();

        service.userEditInsurance(pojisteniID, castka);

        redirectAttributes.addFlashAttribute("success", "Uživatelovo pojištění upraveno");
        return redirectPage + uzivatelID;
    }



    /**
     * SMAZÁNÍ POJIŠTĚNÍ POJIŠTĚNÉHO
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("delete/{id}")
    public String deletePersonInsurance(@PathVariable Long id, RedirectAttributes redirectAttributes){

        //INIT
        Long uzivatelID = service.userInsuranceGetID(id).getUzivatel().getId();

        service.userDeleteUzivatelPojisteni(id);
        redirectAttributes.addFlashAttribute("delete", "Uživatelovo pojištění smazáno");

        return redirectPage + uzivatelID;


    }







}


