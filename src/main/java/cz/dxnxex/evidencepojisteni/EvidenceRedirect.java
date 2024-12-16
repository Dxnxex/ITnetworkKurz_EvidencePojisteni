package cz.dxnxex.evidencepojisteni;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class EvidenceRedirect {


    /**
     * Kontroluje chyby v datech formuláře obsažených v poskytnutém {@code BindingResult}.
     * Pokud jsou přítomny chyby, přidají se jako flash atributy do poskytnutého {@code RedirectAttributes}.
     * Přidává také obecnou chybovou zprávu pro označení přítomnosti chyb na úrovni formuláře.
     *
     * @param result             {@code BindingResult} obsahující výsledky validace a chyby zpracovávaného formuláře
     * @param redirectAttributes {@code RedirectAttributes} pro přidání flash atributů na cílovou stránku
     * @return {@code true}, pokud jsou při validaci zjištěny chyby, jinak {@code false}
     */
    public boolean checkForErrors(BindingResult result, RedirectAttributes redirectAttributes) {
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
