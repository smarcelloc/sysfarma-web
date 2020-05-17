package br.com.sysfarma.util;

import java.io.IOException;
import javax.faces.context.FacesContext;

public class UrlPagina {
    
    public static void redirecionar(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.hashCode());
        }
    }
    
}
