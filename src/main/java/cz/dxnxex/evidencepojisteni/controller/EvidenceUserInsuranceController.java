package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidenceRedirect;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUserInsuranceEntity;
import cz.dxnxex.evidencepojisteni.service.EvidenceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("user/insurance")
public class EvidenceUserInsuranceController {

    private final String returnPage = "pages/user/";
    private final String redirectPage = "redirect:/user/";

    private final EvidenceRedirect redirect = new EvidenceRedirect();

    @Autowired
    private EvidenceUserService service;


    /**
     * ZOBRAZENÍ ÚPRAVY POJIŠTĚNÍ POJIŠTĚNÉHO
     * @param id
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{id}")
    public String renderEditPersonInsurance(@PathVariable Long id, Model model) {

        // Získání uživatele a seznamu pojištění
        EvidenceUserInsuranceEntity user = service.userInsuranceGetID(id);
        model.addAttribute("pojisteni",user);

        return returnPage + "pojisteniEdit";

    }

    /**
     * ÚPRAVA POJIŠTĚNÍ POJIŠTENÉMU
     * @param id
     * @param value
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{id}")
    public String editPersonInsurance(@PathVariable Long id, @RequestParam int value, RedirectAttributes redirectAttributes)  {

        Long userID = service.userInsuranceGetID(id).getUser().getId();
        Long insuranceID = service.userInsuranceGetID(id).getId();

        service.userEditInsurance(insuranceID, value);

        redirectAttributes.addFlashAttribute("success", "Uživatelovo pojištění upraveno");
        return redirectPage + userID;
    }



    /**
     * SMAZÁNÍ POJIŠTĚNÍ POJIŠTĚNÉHO
     * @param id
     * @param redirectAttributes
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("delete/{id}")
    public String deletePersonInsurance(@PathVariable Long id, RedirectAttributes redirectAttributes){

        //INIT
        Long userID = service.userInsuranceGetID(id).getUser().getId();

        service.userDeleteUzivatelPojisteni(id);
        redirectAttributes.addFlashAttribute("delete", "Uživatelovo pojištění smazáno");

        return redirectPage + userID;


    }







}


