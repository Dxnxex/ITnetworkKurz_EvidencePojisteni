package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidencePojisteniRedirect;
import cz.dxnxex.evidencepojisteni.dto.EvidenceAccountDTO;
import cz.dxnxex.evidencepojisteni.models.DuplicateEmailException;
import cz.dxnxex.evidencepojisteni.models.PasswordsDoNotEqualException;
import cz.dxnxex.evidencepojisteni.service.EvidencePojisteniService;
import cz.dxnxex.evidencepojisteni.service.EvidenceUzivatelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class EvidenceControllerAccount {

    private final String returnPage = "pages/";
    private final String redirectPage = "redirect:/account";

    private final EvidencePojisteniRedirect redirect = new EvidencePojisteniRedirect();

    @Autowired
    private EvidenceUzivatelService service;

    @GetMapping("login")
    public String renderLogin() {
        return "/pages/account/login.html";
    }


    @GetMapping("register")
    public String renderRegister(@ModelAttribute("registraceUzivatele") EvidenceAccountDTO account) {
        return "/pages/account/register";
    }

    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute("registraceUzivatele") EvidenceAccountDTO account,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderRegister(account);

        try {
            service.userCreate2(account, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email je již používán.");
            return redirectPage + "/register";

        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "Hesla se nerovnají.");
            result.rejectValue("confirmPassword", "error", "Hesla se nerovnají.");
            return redirectPage + "/register";
        }

        redirectAttributes.addFlashAttribute("success", "Uživatel zaregistrován.");
        return "redirect:/account/login";
    }


}