package cz.dxnxex.evidencepojisteni;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class EvidencePojisteniRedirect {


    //Vytvoření chybové hlášky
    public boolean checkForErrors(BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Chyba: " + result.getFieldError());
            return true;
        }
        return false;
    }


    //BOHUŽEL CHAT-GPT

    // Zkontroluje chyby a přidá flash zprávy do RedirectAttributes
    public boolean checkForErrorsGPT(BindingResult result, RedirectAttributes redirectAttributes) {
        boolean hasErrors = false;

        // Iterace přes všechny FieldError v BindingResult
        for (FieldError fieldError : result.getFieldErrors()) {
            String fieldName = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();

            // Přidání chybových zpráv pro každý chybný formulářový prvek
            redirectAttributes.addFlashAttribute(fieldName, defaultMessage);
            hasErrors = true;
        }

        // Pokud jsou chyby, přidáme chybu pro celý formulář
        if (hasErrors) {
            redirectAttributes.addFlashAttribute("error", "Vyplňte správně formulář!");
        }

        return hasErrors;
    }






}
