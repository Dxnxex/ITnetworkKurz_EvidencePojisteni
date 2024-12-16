package cz.dxnxex.evidencepojisteni.controller;


import cz.dxnxex.evidencepojisteni.EvidenceConfiguration;
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

    private final String returnPage =   "pages/" +      EvidenceConfiguration.PATH_ACCOUNT;
    private final String redirectPage = "redirect:/" +  EvidenceConfiguration.PATH_ACCOUNT;

    @Autowired
    private EvidenceUserService service;

    /**
     * Zobrazí stránku pro přihlášení uživatele.
     *
     * @return Název šablony pro stránku přihlášení
     */
    @GetMapping("/login")
    public String renderLogin() {
        return returnPage + "/login";
    }


    /**
     * Zobrazí stránku pro registraci uživatele.
     *
     * @param account objekt EvidenceAccountDTO sloužící k zachycení dat o registraci uživatele
     * @return Název šablony pro stránku registrace
     */
    @GetMapping("/register")
    public String renderRegister(@ModelAttribute("userRegistration") EvidenceAccountDTO account) {
        return returnPage + "/register";
    }

    /**
     * Zpracuje registraci uživatele.
     *
     * @param account            Objekt EvidenceAccountDTO obsahující registrační údaje uživatele.
     * @param result             Výsledky validace formuláře.
     * @param redirectAttributes Atributy pro přesměrování.
     * @return Název šablony nebo přesměrování na jinou stránku.
     */
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userRegistration") EvidenceAccountDTO account,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return renderRegister(account);
        }

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