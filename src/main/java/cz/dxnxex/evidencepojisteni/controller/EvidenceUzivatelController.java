package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.dto.EvidenceUzivatelDTO;
import cz.dxnxex.evidencepojisteni.entity.EvidencePojisteniEntity;
import cz.dxnxex.evidencepojisteni.entity.EvidenceUzivatelEntity;
import cz.dxnxex.evidencepojisteni.mapper.EvidenceUzivatelMapper;
import cz.dxnxex.evidencepojisteni.service.EvidenceUzivatelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("uzivatele")
public class EvidenceUzivatelController {

    @Autowired
    private EvidenceUzivatelService service;

    @Autowired
    private EvidenceUzivatelMapper mapper;

    //Zobrazení uživatelů
    @GetMapping("")
    public String userIndex(Model model) {

        List<EvidenceUzivatelDTO> uzivatele = service.userGetAll();
        model.addAttribute("uzivatele", uzivatele);

        return "pages/uzivatele/index";
    }

    @PostMapping("")
    public String renderUsers(@Valid @ModelAttribute EvidenceUzivatelDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return userCreateForm(evidence);

        } else {

            return "redirect:/uzivatele";

        }

    }

    //Zobrazení vytvoření uživatele
    @GetMapping("/create")
    public String userCreateForm(@ModelAttribute EvidenceUzivatelDTO evidence) {
        return "pages/uzivatele/create";
    }

    //Odeslání vytvoření uživatele
    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute EvidenceUzivatelDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return userCreateForm(evidence);

        } else {

            service.createUser(evidence);
            return "redirect:/uzivatele";

        }

    }

    //Zobrazení detailu uživatele
    @GetMapping("/{id}")
    public String userShowDetail(@PathVariable Long id, Model model) {

        EvidenceUzivatelEntity uzivatel = service.userGet(id);
        if (uzivatel == null) {throw new IllegalArgumentException("Uživatel s ID " + id + " neexistuje.");}
        model.addAttribute("uzivatel", uzivatel);


        List<EvidencePojisteniEntity> pojisteni = service.pojisteniFindAll();
        model.addAttribute("pojisteni", pojisteni);

        return "pages/uzivatele/detail";
    }


    //Zobrazení editace uživatele
    @GetMapping("/edit/{id}")
    public String userEdit(@PathVariable Long id, Model model, EvidenceUzivatelDTO pojisteni) {


        EvidenceUzivatelEntity uzivatel = service.userGet(id);

        if (uzivatel == null) {throw new IllegalArgumentException("Uživatel s ID " + id + " neexistuje.");}

        model.addAttribute("uzivatel", uzivatel);

        return "pages/uzivatele/edit";
    }



    //Smazání uživatele
    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable Long id){
        service.userDelete(id);
        return "redirect:/uzivatele";
    }

















}
