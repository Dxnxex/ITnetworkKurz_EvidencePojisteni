package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.dto.EvidencePojisteniDTO;
import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pojisteni")
public class EvidencePojisteniController {

    @Autowired
    private EvidencePojisteniService service;


    @GetMapping("")
    public String renderIndex(@ModelAttribute EvidencePojisteniDTO evidence) {
        return "pages/pojisteni/index";
    }

    @GetMapping("/create")
    public String renderCreateForm(@ModelAttribute EvidencePojisteniDTO evidence) {
        return "pages/home/index";
    }

    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute EvidencePojisteniDTO evidence, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Chyba:" + result.getFieldError());
            return renderCreateForm(evidence);

        } else {

            service.createUser2(evidence);
            return "redirect:";

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