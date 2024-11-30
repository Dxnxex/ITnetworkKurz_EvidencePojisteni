package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("pojisteni")
public class EvidencePojisteniController {

    @Autowired
    private EvidencePojisteniService service;


    @GetMapping("")
    public String renderIndex(Model model) {
        List<EvidencePojisteniDTO> uzivatele = service.getAllStream();
        model.addAttribute("pojisteni", uzivatele);
        return "pages/pojisteni/index";
    }

    //Zobrazení vytvoření uživatele
    @GetMapping("/create")
    public String renderCreateForm(@ModelAttribute EvidencePojisteniDTO evidence) {
        return "pages/pojisteni/create";
    }

    //Odeslání vytvoření uživatele
    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute EvidencePojisteniDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return renderCreateForm(evidence);

        } else {

            service.createItem(evidence);
            return "redirect:/pojisteni";

        }

    }


}

/*


    // Zpracování formuláře
    @PostMapping("create")
    public String submitForm(@ModelAttribute EvidencePojisteniDTO evidence) {
        service.createUser2(evidence);
        return "redirect:/";
    }



 */