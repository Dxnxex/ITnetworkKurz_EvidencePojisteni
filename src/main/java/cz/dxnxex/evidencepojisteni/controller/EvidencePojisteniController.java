package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("pojisteni")
public class EvidencePojisteniController {

    private final String returnPage = "pages/pojisteni/";
    private final String redirectPage = "redirect:/pojisteni";

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidencePojisteniService service;

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
        public String renderCreateInsurance(@ModelAttribute EvidencePojisteniDTO evidence) {

        return returnPage + "create";

        }

    /**
     * VYTVOŘENÍ POJIŠTĚNÍ
     * @param pojisteni
     * @param result
     * @param redirectAttributes
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public String createInsurance(@Valid @ModelAttribute EvidencePojisteniDTO pojisteni, BindingResult result, RedirectAttributes redirectAttributes) {

        if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

            return redirectPage + "/" + "create";

        } else {

            service.insuranceCreate(pojisteni);
            redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");
            return redirectPage + "";
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

        EvidencePojisteniEntity pojisteni = service.insuranceGetID(id);
        model.addAttribute("pojisteni", pojisteni);

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
     * @param pojisteni
     * @param result
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{id}")
    public String editInsurance(@PathVariable long id, @Valid EvidencePojisteniDTO pojisteni, BindingResult result, RedirectAttributes redirectAttributes) {

        if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

            return redirectPage + "/edit/" + id;

        } else {

            service.insuranceCreate(pojisteni);
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
