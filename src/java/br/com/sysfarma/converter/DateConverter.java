package br.com.sysfarma.converter;

import br.com.sysfarma.util.Mensagem;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
      
        if (!valor.isEmpty()) {
            String[] d = valor.split("/");

            try {
                return Date.valueOf(d[2] + "-" + d[1] + "-" + d[0]);
            } catch (Exception ex) {
                Mensagem.erro("Erro de validação: A data está inválida.");
                ((UIInput) uic).setValid(false);
            }

        }
        return null;
    }
   

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (!o.toString().isEmpty()) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            return df.format(o);
        }
        return null;
    }

}
