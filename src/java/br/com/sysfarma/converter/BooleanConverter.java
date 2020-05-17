package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("boolean")
public class BooleanConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        return valor.equals("Sim");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o.toString().isEmpty() || o.toString().equals("0") || o.toString().equals("false")) {
            return "Não";
        } else {
            return "Sim";
        }

    }

}
