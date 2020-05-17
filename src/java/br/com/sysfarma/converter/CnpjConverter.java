package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cnpj")
public class CnpjConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (!string.isEmpty()) {
            return string.replaceAll("[^0-9]", "");
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            String valor = o.toString();

            if (valor.length() >= 14) {
                return valor.substring(0, 2) + "." + valor.substring(2, 5) + "." + valor.substring(5, 8) + "/" +  valor.substring(8, 12) + "-" +valor.substring(12, valor.length());
            }
        }

        return null;

    }

}
