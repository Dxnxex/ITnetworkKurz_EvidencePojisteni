package cz.dxnxex.evidencepojisteni.controller;



import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class EvidenceController {

    @Autowired
    private EvidencePojisteniService service;


    @GetMapping("")
    public String renderIndex(Model model) {
        return "pages/home/index";
    }



}
