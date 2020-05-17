package br.com.sysfarma.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.jasper.tagplugins.jstl.Util;

@FacesConverter("titulo")
public class TituloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (!string.isEmpty()) {
            String arr[] = Util.escapeXml(string).trim().split(" ");
            StringBuilder sb = new StringBuilder();

            for (String arrl : arr) {
                if (!arrl.isEmpty()) {
                    sb.append(arrl.substring(0, 1).toUpperCase()).append(arrl.substring(1).toLowerCase()).append(" ");
                }
            }
            return sb.toString().trim();
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
