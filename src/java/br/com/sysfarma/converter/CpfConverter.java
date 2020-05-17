package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpf")
public class CpfConverter implements Converter {

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

            if (valor.length() >= 10) {
                return valor.substring(0, 3) + "." + valor.substring(3, 6) + "." + valor.substring(6, 9) + "-" + valor.substring(9, valor.length());
            }
        }

        return null;

    }

}
