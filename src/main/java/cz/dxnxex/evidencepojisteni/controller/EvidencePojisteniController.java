package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pojisteni")
public class EvidencePojisteniController {

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidencePojisteniService service;

        //Zobrazení [Index & vypsání uživatelů]
        @GetMapping("")
        public String renderPojisteni(Model model) {

            model.addAttribute("vypsatVsechnyPojisteni", service.pojisteniGetAll());
            return "pages/pojisteni/index";

        }

        //Zobrazení [Vytvoření pojíštění]
        @GetMapping("/create")
        public String renderCreateForm(@ModelAttribute EvidencePojisteniDTO evidence) {
            return "pages/pojisteni/create";
        }

        //Odeslání [Vytvoření pojištění]
        @PostMapping("/create")
        public String postCreate(@Valid @ModelAttribute EvidencePojisteniDTO pojisteni, BindingResult result) {


            if (redirect.checkForErrors(result)) {

                return "redirect:/pojisteni";

            } else {

                service.createItem(pojisteni);
                return "redirect:/pojisteni";
            }


        }

        //Zobrazení [Detail pojisteni]
        @GetMapping("/{id}")
        public String renderDetail(@PathVariable Long id, Model model) {

            EvidencePojisteniEntity pojisteni = service.pojisteniGetID(id);

            if (pojisteni == null) {throw new IllegalArgumentException("Pojištění s ID " + id + " neexistuje.");}

            model.addAttribute("pojisteni", pojisteni);

            return "pages/pojisteni/detail";
        }

        //Zobrazení [Upravit pojisteni]
        @GetMapping("edit/{id}")
        public String renderEdit(@PathVariable Long id, Model model) {

            model.addAttribute("pojisteni", service.pojisteniGetID(id));
            return "pages/pojisteni/edit";

        }

        //Odeslání [Upravit pojištění]
        @PostMapping("edit/{id}")
        public String postEdit(@PathVariable long id, @Valid EvidencePojisteniDTO pojisteni, BindingResult result) {

            if (redirect.checkForErrors(result)) {

                return "redirect:/pojisteni";

            } else {

                service.createItem(pojisteni);
                return "redirect:/pojisteni";
            }


        }

        //Odeslání [Smazání pojisteni]
        @PostMapping("/delete/{id}")
        public String postDelete(@PathVariable Long id){
            service.pojisteniDelete(id);
            return "redirect:/pojisteni";
        }



}

/*


 */