package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojistenychDTO;
import cz.dxnxex.evidencepojisteni.service.EvidencePojistenychService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("uzivatele")
public class EvidencePojistenychController {

    @Autowired
    private EvidencePojistenychService service;


    @GetMapping("")
    public String renderIndex(Model model) {
        List<EvidencePojistenychDTO> uzivatele = service.getAllStream();
        model.addAttribute("uzivatele", uzivatele);
        return "pages/uzivatele/index";
    }


    @PostMapping("")
    public String renderUsers(@Valid @ModelAttribute EvidencePojistenychDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return renderCreateForm(evidence);

        } else {

            return "redirect:/uzivatele";

        }

    }

    //Zobrazení vytvoření uživatele
    @GetMapping("/create")
    public String renderCreateForm(@ModelAttribute EvidencePojistenychDTO evidence) {
        return "pages/uzivatele/create";
    }

    //Odeslání vytvoření uživatele
    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute EvidencePojistenychDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return renderCreateForm(evidence);

        } else {

            service.createUser(evidence);
            return "redirect:/uzivatele";

        }

    }

    //Smazání uživatele
    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable Long id){
        service.userDelete(id);
        return "redirect:/uzivatele";
    }

















}
