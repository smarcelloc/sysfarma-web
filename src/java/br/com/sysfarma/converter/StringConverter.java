package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.jasper.tagplugins.jstl.Util;

@FacesConverter("string")
public class StringConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (!string.isEmpty()) {
            return Util.escapeXml(string).trim().toLowerCase();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (!o.toString().isEmpty()) {
            return Util.escapeXml(o.toString()).trim().toLowerCase();
        }
        return null;
    }

}
