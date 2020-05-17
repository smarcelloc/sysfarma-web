package br.com.sysfarma.validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.sysfarma.validator.DataValidator")
public class DataValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        Date dt_atual = new Date();

        if (dt_atual.before(java.sql.Date.valueOf(o.toString()))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops! Data informado não pode ser maior que a atual.", "Ops! Data informado não pode ser maior que a atual.");
            throw new ValidatorException(msg);

        }

    }
}
