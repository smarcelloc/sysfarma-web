package br.com.sysfarma.validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.sysfarma.validator.EmailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        if (!Pattern.compile(EMAIL_PATTERN).matcher(o.toString()).matches()) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail está inválido.", "E-mail está inválido.");
            throw new ValidatorException(msg);

        }
    }
}
