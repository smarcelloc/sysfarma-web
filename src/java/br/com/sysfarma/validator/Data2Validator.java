package br.com.sysfarma.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.sysfarma.validator.Data2Validator")
public class Data2Validator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dt_agora = new Date();
        Date dt = java.sql.Date.valueOf(o.toString());
        
        Date dt_atual = java.sql.Date.valueOf(formatData.format(dt_agora));
        
        if (dt_atual.after(dt) && !dt_atual.equals(dt)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops! Data informado não pode ser menor que a atual.", "Ops! Data informado não pode ser menu que a atual.");
            throw new ValidatorException(msg);

        }

    }
}
