package com.realdolmen.project1.controller;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class CreditCardValidator implements javax.faces.validator.Validator {


    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String ccNumber = (String) value;
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            try {
                int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                if (alternate) {
                        n *= 2;
                        if (n > 9) {
                        n = (n % 10) + 1;
                        }
                }
                sum += n;
                alternate = !alternate;
                }
            catch (NumberFormatException e) {
                throw new ValidatorException(new FacesMessage(" Invalid credit card format!!!!"));
            }
        }
        if (sum % 10 == 0) {
        }
        else {
            throw new ValidatorException(new FacesMessage(" Invalid credit card format!!!!"));
        }
    }
}