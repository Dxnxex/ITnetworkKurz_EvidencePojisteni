package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("uzivatele")
public class EvidenceUzivatelController  {

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidenceUzivatelService service;

    @Autowired
    private EvidenceUzivatelMapper mapper;


        //Zobrazení [Index & vypsání uživatelů]
        @GetMapping("")
        public String renderUzivatele(Model model) {

            model.addAttribute("vypsatVsechnyUzivatele", service.userGetAll());
            return "pages/uzivatele/index";

        }

        //Zobrazení [Vytvoření uživatele]
        @GetMapping("/create")
        public String renderCreate(@ModelAttribute("vytvoreniUzivatele") EvidenceUzivatelDTO uzivatel) {

            return "pages/uzivatele/create";
        }



        //Odeslání [Vytvoření uživatele]
        @PostMapping("/create")
        public String postCreate(@ModelAttribute("vytvoreniUzivatele") EvidenceUzivatelDTO evidence, BindingResult result, RedirectAttributes redirectAttributes) {

            if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

                redirectAttributes.addFlashAttribute("vytvoreniUzivatele", evidence);
                return "redirect:/uzivatele/create";

            } else {

                service.userCreate(evidence);
                redirectAttributes.addFlashAttribute("success", "Uživatel vytvořen.");
                return "redirect:/uzivatele";
            }


        }

    //Zobrazení [Detail uživatele]
    @GetMapping("/{id}")
    public String renderDetail(@PathVariable Long id, Model model, EvidenceUzivatelDTO uzivatelData) {

        // Získání uživatele a seznamu pojištění
        EvidenceUzivatelEntity uzivatel = service.userGetID(id);
        List<EvidencePojisteniEntity> pojisteni = service.pojisteniFindAll();

        model.addAttribute("uzivatel", uzivatel);
        model.addAttribute("pojisteni", pojisteni);


        return "pages/uzivatele/detail";
    }

    //Odeslání [Přiřazení pojištění]
    @PostMapping("/{id}")
    public String pridatPojisteni(@PathVariable Long id, @RequestParam Long pojisteniId) {


        service.pridatPojisteniUzivateli(id, pojisteniId);


        return "redirect:/uzivatele/" + id;
    }


        //Zobrazení [Upravit uživatele]
        @GetMapping("edit/{id}")
        public String renderEdit(@PathVariable Long id, Model model) {

            model.addAttribute("uzivatel", service.userGetID(id));
            return "pages/uzivatele/edit";

        }

        //Odeslání [Upravit uživatele]
        @PostMapping("edit/{id}")
        public String editArticle(@PathVariable long id,@Valid EvidenceUzivatelDTO evidence,BindingResult result, RedirectAttributes redirectAttributes) {

            if (redirect.checkForErrorsGPT(result, redirectAttributes)) {

                redirectAttributes.addFlashAttribute("uzivatel", evidence);
                return "redirect:/uzivatele/edit/{id}";

            } else {

                service.userCreate(evidence);
                redirectAttributes.addFlashAttribute("success", "Uživatel upraven.");
                return "redirect:/uzivatele";
            }


        }

        //Odeslání [Smazání uživatele]
        @PostMapping("/delete/{id}")
        public String userDelete(@PathVariable Long id, RedirectAttributes redirectAttributes){

            service.userDelete(id);
            redirectAttributes.addFlashAttribute("delete", "Uživatel smazán.");
            return "redirect:/uzivatele";

        }








}



/*

 */



