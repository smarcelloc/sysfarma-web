package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.jasper.tagplugins.jstl.Util;

@FacesConverter("estado")
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        string = string.toUpperCase().replaceAll("[^A-Z]", "");

        if (string.length() == 2) {
            return string;
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (!o.toString().isEmpty()) {
            return Util.escapeXml(o.toString()).trim();
        }
        return null;
    }

}
