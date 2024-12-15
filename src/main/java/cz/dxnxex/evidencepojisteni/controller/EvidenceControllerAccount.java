package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.dto.EvidenceAccountDTO;
import cz.dxnxex.evidencepojisteni.exeption.DuplicateEmailException;
import cz.dxnxex.evidencepojisteni.exeption.PasswordsDoNotEqualException;
import cz.dxnxex.evidencepojisteni.service.EvidenceUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("account")
public class EvidenceControllerAccount {

    private final String returnPage = "pages/account/";
    private final String redirectPage = "redirect:/account";

    @Autowired
    private EvidenceUserService service;

    @GetMapping("/login")
    public String renderLogin() {

        return "/pages/account/login.html";

    }


    @GetMapping("/register")
    public String renderRegister(@ModelAttribute("userRegistration") EvidenceAccountDTO account) {
        return returnPage + "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userRegistration") EvidenceAccountDTO account,
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
        return redirectPage + "/login";
    }


}