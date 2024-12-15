package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidenceRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidenceInsuranceDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidenceInsuranceEntity;
import cz.dxnxex.evidencepojisteni.service.EvidenceInsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("insurance")
public class EvidenceInsuranceController {

    private final String returnPage = "pages/insurance/";
    private final String redirectPage = "redirect:/insurance";

    private final EvidenceRedirect redirect = new EvidenceRedirect();

    @Autowired
    private EvidenceInsuranceService service;

    /**
     * ZOBRAZENÍ VŠECH POJIŠTĚNÍ
     * @param model
     * @return
     */
    @GetMapping("")
        public String renderInsurance(Model model) {

            model.addAttribute("vypsatVsechnyPojisteni", service.insuranceGetAllList());
            return returnPage + "index";

        }

    /**
     * ZOBRAZENÍ FORMULÁŘE PRO VYTVOŘENÍ POJÍŠTĚNÍ
     * @param evidence
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
        public String renderCreateInsurance(@ModelAttribute EvidenceInsuranceDTO evidence) {

        return returnPage + "create";

        }

    /**
     * VYTVOŘENÍ POJIŠTĚNÍ
     * @param insurance
     * @param result
     * @param redirectAttributes
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public String createInsurance(@Valid @ModelAttribute EvidenceInsuranceDTO insurance, BindingResult result, RedirectAttributes redirectAttributes) {

        if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

            return redirectPage + "/" + "create";

        } else {

            service.insuranceCreate(insurance);
            redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");
            return redirectPage;
        }

    }

    /**
     * ZOBRAZENÍ DETAILU POJIŠTĚNÍ
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String renderDetailInsurance(@PathVariable Long id, Model model) {

        EvidenceInsuranceEntity insurance = service.insuranceGetID(id);
        model.addAttribute("pojisteni", insurance);

        return returnPage + "detail";
    }

    /**
     * ZOBRAZENÍ ÚPRAVY POJIŠTĚNÍ
     * @param id
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{id}")
    public String renderEditInsurance(@PathVariable Long id, Model model) {

        model.addAttribute("pojisteni", service.insuranceGetID(id));
        return returnPage + "edit";

    }

    /**
     * ÚPRAVA POJIŠTĚNÍ
     * @param id
     * @param insurance
     * @param result
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{id}")
    public String editInsurance(@PathVariable long id, @Valid EvidenceInsuranceDTO insurance, BindingResult result, RedirectAttributes redirectAttributes) {

        if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

            return redirectPage + "/edit/" + id;

        } else {

            service.insuranceCreate(insurance);
            redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");
            return redirectPage + "";
        }

    }


    /**
     * SMAZÁNÍ POJIŠTĚNÍ
     * @param id
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/delete/{id}")
    public String deleteInsurance(@PathVariable Long id, RedirectAttributes redirectAttributes){

        service.insuranceDelete(id);
        redirectAttributes.addFlashAttribute("delete", "Pojištění smazáno");
        return redirectPage + "";

    }



}
